import 'package:flutter/material.dart';

abstract class Style {
  static BoxDecoration boxDecor() {
    return BoxDecoration(
        color: Colors.white,
        border: Border.all(width: 2.0, color: Colors.blue[600]),
        borderRadius: BorderRadius.circular(5.0));
  }

  static SizedBox space() {
    return SizedBox(
      height: 15.0,
    );
  }

  static Container greyBox(BuildContext context, String text) {
    return Container(
      height: 200.0,
      width: MediaQuery.of(context).size.width,
      color: Colors.blueGrey[50],
      child: Column(children: [
        SizedBox(
          height: 75,
        ),
        Icon(Icons.linked_camera),
        Text(text)
      ]),
    );
  }

  static InputDecoration inputDecor(Icon icon, String label, String hint) {
    return InputDecoration(
        prefixIcon: icon,
        labelText: label,
        hintText: hint,
        fillColor: Colors.white,
        focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(5.0),
            borderSide: BorderSide(width: 2.75, color: Colors.blue[600])),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(5.0),
          borderSide: BorderSide(
            color: Colors.blue[600],
            width: 2.0,
          ),
        ));
  }
}