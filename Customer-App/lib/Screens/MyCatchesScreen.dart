import 'package:app2/Screens/Bluetooth.dart';
import 'package:app2/Screens/UpdatesScreen.dart';
import 'package:app2/components/UPI.dart';
import 'package:app2/components/rounded_button.dart';
import 'package:awesome_dialog/awesome_dialog.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:localstorage/localstorage.dart';
import 'package:url_launcher/url_launcher.dart';
import 'Background1.dart';

import 'package:app2/constants.dart';
import 'package:localstorage/localstorage.dart';
// ignore: unused_import
import 'package:geolocator/geolocator.dart';
import 'package:app2/Screens/Login/login_screen.dart';

import 'Signup/signup_screen.dart';

class MyCatchesScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'My Catches',
      debugShowCheckedModeBanner: false,
      theme: _buildShrineTheme(),
      home: CatchList(title: 'List of My Catches'),
      routes: {
        '/updates': (context) => Bluetooth(),
        '/logout': (context) => LoginScreen(),
      },
    );
  }
}

class CatchList extends StatefulWidget {
  final String title;
  CatchList({Key key, this.title}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _CatchListState();
  }
}

class _CatchListState extends State<CatchList> {
  final String apiUrl = URL + "/customer/getMerchants/";
  List<dynamic> _merchants = [];
  final LocalStorage storage = new LocalStorage('localstorage_app');
  Geolocator _geolocator;

  void show_success_dialog(BuildContext context) {
    print("hello");
    AwesomeDialog(
        context: context,
        animType: AnimType.LEFTSLIDE,
        headerAnimationLoop: false,
        dialogType: DialogType.SUCCES,
        title: 'Success',
        desc: 'Signup Success, Hit Ok, to log in',
        btnOkOnPress: () {
          debugPrint("hello from success");
          Navigator.push(context,
              MaterialPageRoute(builder: (context) => MyCatchesScreen()));
        },
        btnOkIcon: Icons.check_circle,
        onDissmissCallback: () {
          debugPrint('Dialog Dissmiss from callback');
        }).show();
  }

  // ignore: non_constant_identifier_names
  void show_error_dialog(BuildContext context) {
    AwesomeDialog(
            context: context,
            dialogType: DialogType.ERROR,
            animType: AnimType.RIGHSLIDE,
            headerAnimationLoop: false,
            title: 'Error',
            desc: 'Some error occured. Please try again.',
            btnOkOnPress: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => MyCatchesScreen()));
            },
            btnOkIcon: Icons.cancel,
            btnOkColor: Colors.red)
        .show();
  }

  void fetchMerchants() async {
    final LocalStorage storage = new LocalStorage('localstorage_app');
    var email = storage.getItem('email').toString();
    var result = await http.get(apiUrl + email);
    setState(() {
      _merchants = json.decode(result.body)['responseObject'];
      print(_merchants);
      debugPrint(_merchants.toList().toString());
    });
  }

  Widget _buildList() {
    return _merchants.length != 0
        ? RefreshIndicator(
            child: ListView.builder(
                padding: EdgeInsets.all(12.0),
                itemCount: _merchants.length,
                itemBuilder: (BuildContext context, int index) {
                  return Card(
                    child: Column(
                      children: [
                        ListTile(
                          leading: Icon(Icons.card_giftcard),
                          title: Text(
                            _merchants[index]['merchant']['storeName']
                                .toString(),
                            style: TextStyle(fontSize: 20),
                          ),
                          subtitle: Text(
                            _merchants[index]['merchant']['phone'].toString(),
                            style:
                                TextStyle(color: Colors.black.withOpacity(0.6)),
                          ),
                        ),
                        Padding(
                          padding: const EdgeInsets.all(16.0),
                          child: Text(
                            "UPI: (Pay here, get invoice on whatsapp)" +
                                _merchants[index]['merchant']['upi'] +
                                "\n\n" +
                                "Address: " +
                                _merchants[index]['merchant']['address'] +
                                "Pincode: " +
                                _merchants[index]['merchant']['pincode']
                                    .toString() +
                                "\n\n" +
                                "Owner Name: " +
                                _merchants[index]['merchant']['ownerName'],
                            style: TextStyle(
                                color: Colors.black.withOpacity(0.6),
                                fontSize: 18),
                          ),
                        ),
                        (_merchants[index]['image'] == null)
                            ? Image.asset('assets/images/merchant.jpg')
                            : Image.network(_merchants[index]['image']),
                        SizedBox(height: 20),
                        _merchants[index]['subscribed'] == false
                            ? RoundedButton(
                                text: "Subscribe",
                                press: () async {
                                  final uri = URL + "/customer/subscribe";
                                  Map requestBody = {
                                    "custEmail": "raghav.ddps2@gmail.com",
                                    "merchantUsername": _merchants[index]
                                        ['merchant']['username']
                                  };

                                  String body = json.encode(requestBody);
                                  print(requestBody);
                                  http.Response response = await http.post(
                                    uri,
                                    headers: {
                                      "Content-Type": "application/json"
                                    },
                                    body: body,
                                  );
                                  final responseBody =
                                      json.decode(response.body);
                                  if (responseBody["success"] == true) {
                                    show_success_dialog(context);
                                  } else {
                                    show_error_dialog(context);
                                  }
                                })
                            : RoundedButton(
                                text: "Already Subscribed",
                                press: () {},
                                color: Colors.green,
                              ),
                        RoundedButton(
                            text: "See Offers",
                            press: () {
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => UpdatesScreen(
                                          username: _merchants[index]
                                              ['merchant']['username'],
                                          storeName: _merchants[index]
                                              ['merchant']['storeName'])));
                            }),
                        RoundedButton(
                            text: "Call",
                            press: () async {
                              var number = _merchants[index]['merchant']
                                      ['phone']
                                  .substring(0, 10);
                              //
                              print(number);
                              launch("tel:" + number);
                            }),
                        RoundedButton(
                            text: "Pay",
                            press: () {
                              var upi = _merchants[index]['merchant']['upi'];
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => UPI()));
                            }),
                      ],
                    ),
                  );
                }),
            onRefresh: _getData,
          )
        : Center(child: CircularProgressIndicator());
  }

//solved
  Future<void> _getData() async {
    setState(() {
      fetchMerchants();
    });
  }

  void checkPermission() {
    _geolocator.checkGeolocationPermissionStatus().then((status) {
      print('status: $status');
    });
    _geolocator
        .checkGeolocationPermissionStatus(
            locationPermission: GeolocationPermission.locationAlways)
        .then((status) {
      print('always status: $status');
    });
    _geolocator.checkGeolocationPermissionStatus(
        locationPermission: GeolocationPermission.locationWhenInUse)
      ..then((status) {
        print('whenInUse status: $status');
      });
  }

//solved
  @override
  void initState() {
    super.initState();
    _geolocator = Geolocator();
    checkPermission();
    fetchMerchants();
  }

  @override
  Widget build(BuildContext context) {
    return Background1(
      child: Scaffold(
        appBar: AppBar(title: const Text('Shops Nearby')),
        drawer: navigationDrawer(),
        body: Container(
          child: _buildList(),
        ),
      ),
    );
  }
}

IconThemeData _customIconTheme(IconThemeData original) {
  return original.copyWith(color: shrineBrown900);
}

ThemeData _buildShrineTheme() {
  final ThemeData base = ThemeData.light();
  return base.copyWith(
    colorScheme: _shrineColorScheme,
    accentColor: shrineBrown900,
    primaryColor: shrinePink100,
    buttonColor: shrinePink100,
    scaffoldBackgroundColor: shrineBackgroundWhite,
    cardColor: shrineBackgroundWhite,
    textSelectionColor: shrinePink100,
    errorColor: shrineErrorRed,
    buttonTheme: const ButtonThemeData(
      colorScheme: _shrineColorScheme,
      textTheme: ButtonTextTheme.normal,
    ),
    primaryIconTheme: _customIconTheme(base.iconTheme),
    textTheme: _buildShrineTextTheme(base.textTheme),
    primaryTextTheme: _buildShrineTextTheme(base.primaryTextTheme),
    accentTextTheme: _buildShrineTextTheme(base.accentTextTheme),
    iconTheme: _customIconTheme(base.iconTheme),
  );
}

TextTheme _buildShrineTextTheme(TextTheme base) {
  return base
      .copyWith(
        headline1: base.headline1.copyWith(
          fontWeight: FontWeight.w500,
          letterSpacing: defaultLetterSpacing,
        ),
        caption: base.caption.copyWith(
          fontWeight: FontWeight.w400,
          fontSize: 14,
          letterSpacing: defaultLetterSpacing,
        ),
        bodyText1: base.bodyText1.copyWith(
          fontWeight: FontWeight.w500,
          fontSize: 16,
          letterSpacing: defaultLetterSpacing,
        ),
        bodyText2: base.bodyText2.copyWith(
          letterSpacing: defaultLetterSpacing,
        ),
        subtitle1: base.subtitle1.copyWith(
          letterSpacing: defaultLetterSpacing,
        ),
        subtitle2: base.subtitle2.copyWith(
          letterSpacing: defaultLetterSpacing,
        ),
        button: base.button.copyWith(
          fontWeight: FontWeight.w500,
          fontSize: 14,
          letterSpacing: defaultLetterSpacing,
        ),
      )
      .apply(
        fontFamily: 'Rubik',
        displayColor: shrineBrown900,
        bodyColor: shrineBrown900,
      );
}

const ColorScheme _shrineColorScheme = ColorScheme(
  primary: shrinePink100,
  primaryVariant: shrineBrown900,
  secondary: shrinePink50,
  secondaryVariant: shrineBrown900,
  surface: shrineSurfaceWhite,
  background: shrineBackgroundWhite,
  error: shrineErrorRed,
  onPrimary: shrineBrown900,
  onSecondary: shrineBrown900,
  onSurface: shrineBrown900,
  onBackground: shrineBrown900,
  onError: shrineSurfaceWhite,
  brightness: Brightness.light,
);

const Color shrinePink50 = Color(0xFFFEEAE6);
const Color shrinePink100 = Color(0xFFFEDBD0);
const Color shrinePink300 = Color(0xFFFBB8AC);
const Color shrinePink400 = Color(0xFFEAA4A4);

const Color shrineBrown900 = Color(0xFF442B2D);
const Color shrineBrown600 = Color(0xFF7D4F52);

const Color shrineErrorRed = Color(0xFFC5032B);

const Color shrineSurfaceWhite = Color(0xFFFFFBFA);
const Color shrineBackgroundWhite = Colors.white;

const defaultLetterSpacing = 0.03;

//solved
class navigationDrawer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Drawer(
        child: Container(
      color: shrinePink100,
      child: ListView(
        children: <Widget>[
          createDrawerHeader(),
          createDrawerBodyItem(
            icon: Icons.update,
            text: 'RealTime offers - Instore',
            onTap: () => Navigator.pushNamed(context, '/updates'),
          ),
          createDrawerBodyItem(
              icon: Icons.record_voice_over,
              text: 'Logout',
              onTap: () => Navigator.pushNamed(context, '/logout')),
          ListTile(
            title: Text('2.0.0', style: TextStyle(color: Colors.black)),
            onTap: () {},
          ),
        ],
      ),
    ));
  }
}

//solved
Widget createDrawerBodyItem(
    {IconData icon, String text, GestureTapCallback onTap}) {
  return ListTile(
    title: Row(
      children: <Widget>[
        Icon(
          icon,
          color: Colors.blue,
        ),
        Padding(
          padding: EdgeInsets.only(left: 18.0),
          child: Text(text,
              style: TextStyle(
                  color: Colors.black,
                  fontSize: 16,
                  fontWeight: FontWeight.w400)),
        ),
      ],
    ),
    onTap: onTap,
  );
}

//solved
Widget createDrawerHeader() {
  final LocalStorage storage = new LocalStorage('localstorage_app');
  var email = storage.getItem('email').toString();
  return DrawerHeader(
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: <Widget>[
        Text(
          'Welcome ' + email,
          style: TextStyle(
              color: Colors.black, fontSize: 16.0, fontWeight: FontWeight.w600),
        ),
      ],
    ),
  );
}
