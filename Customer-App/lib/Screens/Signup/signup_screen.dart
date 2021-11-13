import 'package:flutter/material.dart';
import 'package:app2/Screens/Signup/components/body.dart';

class SignUpScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Body("Raghav", "560029", "raghav.ddps2@gmil.com", "raghav@123M",
          "8384852943", "bangalore"),
    );
  }
}
