import 'dart:async';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:url_launcher/url_launcher.dart';
import '../constants.dart';
import 'Background1.dart';

_launchURL(url) async {
  if (await canLaunch(url)) {
    await launch(url);
  } else {
    throw 'Could not launch $url';
  }
}

class UpdatesScreen extends StatelessWidget {
  final String username;
  final String storeName;
  UpdatesScreen({Key key, @required this.username, @required this.storeName})
      : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MyApp1(username, storeName);
  }
}

class MyApp1 extends StatelessWidget {
  final String username;
  final String storeName;
  MyApp1(this.username, this.storeName);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text(storeName + ' Live Offers')),
        body: CatchList(
          username: this.username,
        ));
  }
}

class CatchList extends StatefulWidget {
  final String title;
  final String username;
  CatchList({Key key, this.title, this.username}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _CatchListState();
  }
}

class _CatchListState extends State<CatchList> {
  String apiUrl = URL + "/customer/getOffers/";
  List<dynamic> _offers = [];
  var username = "raghavddps2";
  void fetchCatches() async {
    print(apiUrl);
    this.username = widget.username;
    apiUrl += widget.username;
    var result = await http.get(apiUrl);
    setState(() {
      _offers = json.decode(result.body)['responseObject'];
      debugPrint(_offers.toList().toString());
    });
  }

  Widget _buildList() {
    return _offers.length != 0
        ? RefreshIndicator(
            child: ListView.builder(
                padding: EdgeInsets.all(15.0),
                itemCount: _offers.length,
                itemBuilder: (BuildContext context, int index) {
                  print("hi");
                  print(_offers[index]);
                  return InkWell(
                    child: Card(
                      color: Colors.white,
                      child: Column(
                        children: [
                          ListTile(
                            leading: Icon(Icons.notification_important),
                            title: Text(
                              _offers[index]['offerName'],
                              style: TextStyle(fontSize: 20),
                            ),
                            subtitle: Text(
                              "Created At: " +
                                  _offers[index]['createdAt'] +
                                  "\n"
                                      "Valid Till: " +
                                  _offers[index]['validTill'] +
                                  "\n"
                                      "Offer Type: " +
                                  _offers[index]['offerType'] +
                                  "\n",
                              style: TextStyle(
                                  color: Colors.black.withOpacity(0.6)),
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.all(16.0),
                            child: Text(
                              _offers[index]['offerDetails'],
                              style: TextStyle(
                                  color: Colors.black.withOpacity(0.6),
                                  fontSize: 18),
                            ),
                          ),
                          (_offers[index]['imageAssociated'] == null)
                              ? Image.asset('assets/images/offer.jpg')
                              : Image.network(
                                  _offers[index]['imageAssociated']),
                          SizedBox(height: 30),
                        ],
                      ),
                    ),
                  );
                }),
            onRefresh: _getData,
          )
        : Center(child: CircularProgressIndicator());
  }

  Future<void> _getData() async {
    setState(() {
      fetchCatches();
    });
  }

  @override
  void initState() {
    super.initState();
    fetchCatches();
  }

  @override
  Widget build(BuildContext context) {
    return Background1(
      child: Scaffold(
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
