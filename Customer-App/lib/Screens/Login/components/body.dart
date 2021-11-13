import 'package:app2/Screens/Login/login_screen.dart';
import 'package:app2/Screens/MyCatchesScreen.dart';
import 'package:flutter/material.dart';
import 'package:app2/Screens/Login/components/background.dart';
import 'package:app2/Screens/Signup/signup_screen.dart';
import 'package:app2/components/already_have_an_account_acheck.dart';
import 'package:app2/components/rounded_button.dart';
import 'package:app2/components/rounded_input_field.dart';
import 'package:app2/components/rounded_password_field.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:localstorage/localstorage.dart';
import 'package:awesome_dialog/awesome_dialog.dart';

import '../../../constants.dart';

// ignore: must_be_immutable
class Body extends StatelessWidget {
  final LocalStorage storage = new LocalStorage('localstorage_app');
  var email, password;

  Body(this.email, this.password);
  // ignore: non_constant_identifier_names
  void show_success_dialog(BuildContext context, var responseBody) {
    print("hello");
    AwesomeDialog(
        context: context,
        animType: AnimType.LEFTSLIDE,
        headerAnimationLoop: false,
        dialogType: DialogType.SUCCES,
        title: 'Success',
        desc: 'Login Success, Hit Ok, to log in',
        btnOkOnPress: () {
          debugPrint("hello from success");
          storage.setItem('email', responseBody['responseObject']['email']);
          Navigator.push(context,
              MaterialPageRoute(builder: (context) => MyCatchesScreen()));
        },
        btnOkIcon: Icons.check_circle,
        onDissmissCallback: () {
          debugPrint('Dialog Dissmiss from callback');
        }).show();
  }

  // ignore: non_constant_identifier_names
  void show_error_dialog(BuildContext context, error) {
    AwesomeDialog(
            context: context,
            dialogType: DialogType.ERROR,
            animType: AnimType.RIGHSLIDE,
            headerAnimationLoop: false,
            title: 'Error',
            desc: error,
            btnOkOnPress: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => LoginScreen()));
            },
            btnOkIcon: Icons.cancel,
            btnOkColor: Colors.red)
        .show();
  }

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Background(
      child: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              "LOGIN",
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
            SizedBox(height: size.height * 0.03),
            Image.asset(
              "assets/images/cashop.png",
              height: size.height * 0.35,
            ),
            SizedBox(height: size.height * 0.03),
            RoundedInputField(
              hintText: "Your Email",
              onChanged: (value) {
                print(value);
                this.email = value;
              },
            ),
            RoundedPasswordField(
              onChanged: (value) {
                print(value);
                this.password = value;
              },
            ),
            RoundedButton(
              text: "LOGIN",
              press: () async {
                print("HI");
                print(email);

                final uri = URL + "/customer/login";
                Map requestBody = {
                  "password": this.password,
                  "email": this.email
                };

                String body = json.encode(requestBody);
                print(requestBody);
                http.Response response = await http.post(
                  uri,
                  headers: {"Content-Type": "application/json"},
                  body: body,
                );
                final responseBody = json.decode(response.body);
                if (responseBody["success"] == true) {
                  show_success_dialog(context, responseBody);
                } else {
                  show_error_dialog(context, responseBody["message"]);
                }
              },
            ),
            SizedBox(height: size.height * 0.03),
            AlreadyHaveAnAccountCheck(
              press: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) {
                      return SignUpScreen();
                    },
                  ),
                );
              },
            ),
          ],
        ),
      ),
    );
  }
}
