import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:miauff/Screens/Home.dart';
import 'package:miauff/Screens/Welcome.dart';
import 'package:miauff/Screens/Register.dart';
import 'package:miauff/Screens/Check.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(Miauff());
}

class Miauff extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      initialRoute: Check.id,
      routes: {
        Check.id: (context) => Check(),
        Welcoming.id: (context) => Welcoming(),
        Register.id: (context) => Register(),
        MyHome.id: (context) => MyHome(),
      },
    );
  }
}
