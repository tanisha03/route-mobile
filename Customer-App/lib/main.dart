import 'package:app2/Screens/Welcome/welcome_screen.dart';
import 'package:app2/constants.dart';
import 'package:flutter/material.dart';
import 'package:intro_slider/intro_slider.dart';
import 'package:intro_slider/slide_object.dart';

void main() {
  runApp(BasicApp());
}

class BasicApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Jan Dhan Drashak',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: IntroScreen(),
    );
  }
}

class IntroScreen extends StatefulWidget {
  @override
  _IntroScreenState createState() => _IntroScreenState();
}

class _IntroScreenState extends State<IntroScreen> {
  List<Slide> slides = new List();

  @override
  void initState() {
    super.initState();

    slides.add(
      new Slide(
        title: "Discover Nearby offers",
        styleTitle: TextStyle(
            color: Colors.black, fontSize: 25, fontFamily: 'Quicksand'),
        description: "Discover amazing deals nearby and subscribe to them",
        styleDescription: TextStyle(
            color: Colors.black, fontSize: 14, fontFamily: 'Quicksand'),
        pathImage: "assets/images/cashop.png",
        heightImage: 125.0,
        widthImage: 100.0,
        backgroundColor: const Color(0xf2f8f9ff),
      ),
    );

    slides.add(
      new Slide(
        title: "Get Exclusive offers",
        styleTitle: TextStyle(
            color: Colors.black, fontSize: 25, fontFamily: 'Quicksand'),
        description: "Get Exclusive custom tailored offers for yourself.",
        styleDescription: TextStyle(
            color: Colors.black, fontSize: 14, fontFamily: 'Quicksand'),
        pathImage: "assets/images/cashop.png",
        heightImage: 125.0,
        widthImage: 100.0,
        backgroundColor: const Color(0xf2f8f9ff),
      ),
    );
    slides.add(
      new Slide(
        title: "Improve your in store experience",
        styleTitle: TextStyle(
            color: Colors.black, fontSize: 22, fontFamily: 'Quicksand'),
        description:
            "Improve your in store experience by receiving real time in store guide and customized offers even Offline",
        styleDescription: TextStyle(
            color: Colors.black, fontSize: 14, fontFamily: 'Quicksand'),
        pathImage: "assets/images/cashop.png",
        heightImage: 125.0,
        widthImage: 100.0,
        backgroundColor: const Color(0xf2f8f9ff),
      ),
    );
  }

  void onDonePress() {
    // TODO: go to next screen
    Navigator.of(context)
        .push(MaterialPageRoute(builder: (context) => MyApp()));
  }

  void onSkipPress() {
    // TODO: go to next screen
    onDonePress();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: IntroSlider(
            slides: this.slides,
            onDonePress: this.onDonePress,
            onSkipPress: this.onSkipPress,
            nameDoneBtn: 'Done',
            nameNextBtn: 'Next',
            nameSkipBtn: 'Skip',
            colorSkipBtn: Colors.blue[600],
            colorPrevBtn: Colors.blue[600],
            colorDoneBtn: Colors.blue[600],
            // styleNameDoneBtn:
            //     TextStyle(color: Colors.blue[600], fontSize: 16.0),
            // styleNamePrevBtn:
            //     TextStyle(color: Colors.blue[600], fontSize: 16.0),
            // styleNameSkipBtn:
            //     TextStyle(color: Colors.blue[600], fontSize: 16.0),
            colorActiveDot: Colors.blue[600],
            colorDot: Colors.grey));
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Fisherman App',
      theme: ThemeData(
          primaryColor: kPrimaryColor, scaffoldBackgroundColor: Colors.white),
      home: WelcomeScreen(),
    );
  }
}
