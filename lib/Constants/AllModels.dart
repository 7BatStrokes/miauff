import 'package:flutter/material.dart';
import 'package:miauff/Constants/Decorations.dart';

List<Color> lesCols = [
  Colors.yellowAccent[700],
  Colors.yellowAccent[400],
  Colors.yellowAccent[200],
  Colors.yellowAccent[100],
  Colors.yellow[100],
  Colors.white,
  Colors.black,
];

List tempcols = lesCols.toList();

// ignore: must_be_immutable
class TheTextpls extends StatelessWidget {
  TheTextpls({
    this.myicon,
    this.helper,
    this.onPressed,
    this.title,
    this.txt,
    this.obstxt,
    this.txtcol,
    this.fillcol,
    this.bordcol,
    this.entertxt,
    this.fon,
  });

  final Function onPressed;
  final String title;
  final String txt;
  final bool entertxt;
  IconData myicon;
  String helper;
  bool obstxt;
  Color bordcol;
  Color txtcol;
  Color fillcol;
  double fon;

  @override
  Widget build(BuildContext context) {
    if (obstxt == null) {
      obstxt = false;
    }
    if (helper == null) {
      helper = "";
    }
    if (txtcol == null) {
      txtcol = lesCols[0];
    }
    if (fillcol == null) {
      fillcol = lesCols[4];
    }
    if (fon == null) {
      fon = 14;
    }
    if (bordcol == null) {
      bordcol = lesCols[5];
    }
    return TextField(
      enabled: entertxt,
      onChanged: onPressed,
      cursorColor: lesCols[1],
      style:
          TextStyle(color: txtcol, fontFamily: "Manrope Light", fontSize: fon),
      obscureText: obstxt,
      decoration: Universe.txtplsdeco.copyWith(
          isDense: true,
          prefixIcon: Icon(
            myicon,
            color: lesCols[6],
            size: 30,
          ),
          helperText: helper,
          helperStyle: TextStyle(color: txtcol),
          hintText: title,
          errorText: txt,
          fillColor: fillcol,
          hintStyle: TextStyle(color: txtcol),
          enabledBorder: OutlineInputBorder(
            borderSide: BorderSide(color: bordcol, width: 1),
            borderRadius: BorderRadius.all(Radius.circular(32)),
          ),
          focusedBorder: OutlineInputBorder(
            borderSide: BorderSide(color: lesCols[1], width: 2),
            borderRadius: BorderRadius.all(Radius.circular(32)),
          )),
    );
  }
}

class PlayContainer extends StatelessWidget {
  PlayContainer(
      {this.colorstart,
      this.colorend,
      this.coloricon,
      this.colorsub,
      this.icon,
      this.title,
      this.subtitle,
      this.func});

  final String title;
  final String subtitle;
  final IconData icon;
  final Color colorstart;
  final Color colorend;
  final Color coloricon;
  final Color colorsub;
  final Function func;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: func,
      child: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(25),
          border: Border(
            left: BorderSide(
                color: Colors.white, style: BorderStyle.solid, width: 1.5),
            right: BorderSide(
                color: Colors.white, style: BorderStyle.solid, width: 1.5),
            top: BorderSide(
                color: Colors.white, style: BorderStyle.solid, width: 1.5),
            bottom: BorderSide(
                color: Colors.white, style: BorderStyle.solid, width: 1.5),
          ),
        ),
        child: Container(
          clipBehavior: Clip.antiAlias,
          constraints: BoxConstraints.expand(
              height: MediaQuery.of(context).size.height * 0.25,
              width: MediaQuery.of(context).size.width * 0.9),
          child: Stack(
            clipBehavior: Clip.hardEdge,
            children: <Widget>[
              Positioned(
                right: 5,
                top: 1,
                child: Icon(
                  icon,
                  size: 200,
                  color: coloricon,
                ),
              ),
              Positioned(
                top: 5,
                left: 4,
                child: Text(
                  title,
                  style: TextStyle(
                      fontFamily: "Manrope Light",
                      color: Colors.white,
                      fontWeight: FontWeight.bold,
                      fontSize: 50),
                ),
              ),
              Positioned(
                bottom: 5,
                left: 15,
                child: Text(
                  subtitle,
                  style: TextStyle(
                      fontFamily: "Manrope Light",
                      color: colorsub,
                      fontWeight: FontWeight.bold,
                      fontSize: 20),
                ),
              ),
            ],
          ),
          decoration: BoxDecoration(
            gradient: LinearGradient(
                begin: AlignmentDirectional.topStart,
                end: Alignment.bottomRight,
                colors: [colorstart, colorend]),
            borderRadius: BorderRadius.circular(25),
          ),
        ),
      ),
    );
  }
}

class CustAppBar extends StatelessWidget implements PreferredSizeWidget {
  final Text username;
  final dynamic profpic;
  final Color status;
  final List<Widget> actions;
  final Widget leading;

  CustAppBar(
      {Key key,
      this.username,
      this.profpic,
      this.status,
      this.actions,
      this.leading})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(10),
      decoration: BoxDecoration(
          color: Colors.lightBlue,
          border: Border(
              bottom: BorderSide(
                  color: Colors.red, style: BorderStyle.solid, width: 1.5))),
      child: AppBar(
        elevation: 0,
        leading: leading,
        backgroundColor: Colors.green,
        title: Row(
          mainAxisSize: MainAxisSize.min,
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Stack(
              children: <Widget>[
                Align(
                  alignment: AlignmentDirectional.center,
                  child: CircleAvatar(
                    backgroundColor: Colors.blueGrey,
                    radius: 10,
                    backgroundImage: AssetImage(profpic),
                  ),
                ),
                Align(
                    alignment: AlignmentDirectional.topEnd,
                    child: CircleAvatar(
                      backgroundColor: Colors.green,
                      radius: 5,
                      child: CircleAvatar(),
                    )),
                Container(
                  decoration: BoxDecoration(
                    shape: BoxShape.circle,
                    color: Colors.green,
                  ),
                  child: CircleAvatar(
                    backgroundColor: status,
                    minRadius: 5,
                  ),
                )
              ],
            ),
            username
          ],
        ),
        actions: actions,
      ),
    );
  }

  final Size preferredSize = Size.fromHeight(kToolbarHeight + 5);
}
