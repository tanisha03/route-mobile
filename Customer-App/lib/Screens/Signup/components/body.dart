import 'dart:convert';

import 'package:app2/Screens/Signup/signup_screen.dart';
import 'package:flutter/material.dart';
import 'package:app2/Screens/Login/login_screen.dart';
import 'package:app2/Screens/Signup/components/background.dart';
import 'package:app2/components/already_have_an_account_acheck.dart';
import 'package:app2/components/rounded_button.dart';
import 'package:app2/components/rounded_input_field.dart';
import 'package:app2/components/rounded_password_field.dart';
import 'dart:math';
import 'package:http/http.dart' as http;

import 'package:awesome_dialog/awesome_dialog.dart';

import '../../../constants.dart';

//ignore: must_be_immutable
class Body extends StatelessWidget {
  var name, pincode, email, password, mobileNo, city;
  Body(this.name, this.pincode, this.email, this.password, this.mobileNo,
      this.city);

  // ignore: non_constant_identifier_names
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
          Navigator.push(
              context, MaterialPageRoute(builder: (context) => LoginScreen()));
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
                  MaterialPageRoute(builder: (context) => SignUpScreen()));
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
              "SIGNUP",
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
            SizedBox(height: size.height * 0.03),
            Image.asset(
              "assets/images/cashop.png",
              height: size.height * 0.35,
            ),
            RoundedInputField(
              hintText: "Your Name",
              onChanged: (value) {
                this.name = value;
              },
            ),
            RoundedInputField(
              hintText: "Your Email",
              onChanged: (value) {
                this.email = value;
              },
            ),
            RoundedPasswordField(
              onChanged: (value) {
                this.password = value;
              },
            ),
            RoundedInputField(
              hintText: "Your Mobile Number",
              onChanged: (value) {
                this.mobileNo = value;
              },
            ),
            RoundedInputField(
              hintText: "Your City",
              onChanged: (value) {
                this.city = city;
              },
            ),
            RoundedInputField(
                hintText: "Pincode",
                onChanged: (value) {
                  this.pincode = value;
                }),
            RoundedButton(
              text: "SIGNUP",
              press: () async {
                final uri = URL + "/customer/create";
                Map requestBody = {
                  "email": this.email,
                  "password": this.password,
                  "mobile": this.mobileNo,
                  "city": this.city,
                  "pincode": int.parse(this.pincode)
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
                  show_success_dialog(context);
                } else {
                  show_error_dialog(context);
                }
              },
            ),
            SizedBox(height: size.height * 0.03),
            AlreadyHaveAnAccountCheck(
              login: false,
              press: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) {
                      return LoginScreen();
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
