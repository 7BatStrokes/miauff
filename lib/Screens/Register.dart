import 'dart:io';
import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:miauff/Constants/AllModels.dart';
import 'package:miauff/Constants/AppRepository.dart';
import 'package:miauff/Screens/Welcome.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:miauff/Constants/FirebaseRepository.dart';

import 'MainMenu.dart';

GoogleSignIn _googleSignIn = GoogleSignIn(scopes: ["email"]);
final fire = FirebaseFirestore.instance;
User loggedInUser;
FireRepo _firerepo = FireRepo();
AppRepo _apprepo = AppRepo();

class Register extends StatefulWidget {
  static const String id = 'reg';
  @override
  _RegisterState createState() => _RegisterState();
}

class _RegisterState extends State<Register> {
  final author = FirebaseAuth.instance;

  bool noConnecting = true;

  PickedFile file;
  double rad = 15;
  String day;
  String month;
  String year;
  String goog = "images/googleicon.png";
  dynamic fileimagen;

  static String username;
  static String email;
  static String pas;
  static String pas2;
  static String allusers;

  Widget usernamewidg = TheTextpls(
    title: "Give yourself a cool Username",
    onPressed: (value) {
      username = _apprepo.trimstr(value);
      print(username);
    },
  );
  Widget emailwidg = TheTextpls(
    title: "Enter your email, don't worry we won't spam",
    onPressed: (value) {
      email = _apprepo.trimstr(value);
    },
  );
  Widget paswidg = TheTextpls(
    title: "This text field is asking for your password",
    obstxt: true,
    onPressed: (value) {
      pas = value;
    },
  );
  Widget pas2widg = TheTextpls(
    title: "I'm asking for it again, hope you don´t mind :)",
    obstxt: true,
    onPressed: (value) {
      pas2 = value;
    },
  );
  Widget profpicState = Icon(
    Icons.add,
    color: lesCols[6],
    size: 50,
  );

  void authenticateUser(User user, String username) {
    _firerepo.authenticateUser(user).then((isNewUser) {
      if (isNewUser) {
        _firerepo.addUserDatatoDb(user, username, "").then((value) {
          Navigator.pushReplacement(context,
              MaterialPageRoute(builder: (context) {
            return Menu(0);
          }));
        });
      } else {
        _firerepo.sadlySignOut();
        Navigator.pushReplacement(context,
            MaterialPageRoute(builder: (context) {
          return Welcoming();
        }));
      }
    });
  }

  void performSignUp() async {
    if (username.isNotEmpty) {
      bool isSignedIn = await _firerepo.isSignedIn();
      if (_apprepo.wording(allusers, username)) {
        try {
          if (isSignedIn) {
            try {
              setState(() {
                noConnecting = false;
              });
              _firerepo.gSignIn("y").then((User user) {
                if (user != null) {
                  authenticateUser(user, username);
                } else {
                  setState(() {
                    noConnecting = true;
                  });
                  print("There was an error");
                }
              });
            } catch (e) {}
          } else {
            if (pas == pas2) {
              try {
                setState(() {
                  noConnecting = false;
                });
                final user = await FirebaseAuth.instance
                    .createUserWithEmailAndPassword(
                        email: email, password: pas);
                if (user != null) {
                  _firerepo.addUserDatatoDb(user.user, username, email);
                  Navigator.pushReplacement(context,
                      MaterialPageRoute(builder: (context) {
                    return Menu(0);
                  }));
                }
              } catch (E) {}
            } else if (pas != pas2) {
              setState(() {
                noConnecting = true;
                paswidg = TheTextpls(
                  txt:
                      "Hmm... Looks like you'll have to rewrite both passwords",
                  title: "This text field is asking for your password",
                  obstxt: true,
                  onPressed: (value) {
                    pas = value;
                  },
                );
                pas2widg = TheTextpls(
                  txt: "",
                  title: "I'm asking for it again, hope you don´t mind :)",
                  obstxt: true,
                  onPressed: (value) {
                    pas2 = value;
                  },
                );
              });
            }
          }
        } catch (e) {}
      } else {
        setState(
          () {
            usernamewidg = TheTextpls(
              title: "Give yourself an even cooler Username",
              txt: "Sorry that Username is already in use",
              onPressed: (value) {
                username = _apprepo.trimstr(value);
              },
            );
          },
        );
      }
    } else {
      setState(() {
        usernamewidg = TheTextpls(
          title: "Yeah we're still waiting for your username...",
          txt: "Username can't be empty",
          onPressed: (value) {
            username = _apprepo.trimstr(value);
          },
        );
      });
    }
  }

  Future<bool> _onWillPop() async {
    return (await showDialog(
          context: context,
          builder: (context) => AlertDialog(
            title: Text('You sure you want to go back?',
                style: TextStyle(
                    color: lesCols[6],
                    fontFamily: "Manrope Light",
                    fontWeight: FontWeight.bold,
                    fontSize: 22)),
            content: Text('Everything here will be removed.',
                style: TextStyle(
                    color: lesCols[6],
                    fontFamily: "Manrope Light",
                    fontWeight: FontWeight.normal,
                    fontSize: 16)),
            actions: <Widget>[
              TextButton(
                onPressed: () {
                  Navigator.of(context).pop(false);
                },
                child: Text('No',
                    style: TextStyle(
                        color: lesCols[0],
                        fontFamily: "Manrope Light",
                        fontWeight: FontWeight.bold,
                        fontSize: 13)),
              ),
              TextButton(
                  onPressed: () {
                    _firerepo.sadlySignOut();
                    Navigator.pushReplacement(context,
                        MaterialPageRoute(builder: (context) {
                      return Welcoming();
                    }));
                  },
                  child: Text('Yes',
                      style: TextStyle(
                          color: lesCols[0],
                          fontFamily: "Manrope Light",
                          fontWeight: FontWeight.bold,
                          fontSize: 13))),
            ],
          ),
        )) ??
        false;
  }

  @override
  Widget build(BuildContext context) {
    try {
      fire.collection("names").get().then((QuerySnapshot snapshot) {
        snapshot.docs.forEach((f) {
          allusers = ('${f.data()["usernames"]}');
        });
      });
    } catch (E) {}
    return WillPopScope(
      onWillPop: _onWillPop,
      child: Scaffold(
        backgroundColor: lesCols[5],
        appBar: AppBar(
          bottom: PreferredSize(
              child: Stack(
                children: [
                  Divider(
                    color: lesCols[4],
                    thickness: 4,
                  ),
                  Divider(
                    thickness: 4,
                    endIndent: 20,
                    color: lesCols[1],
                  ),
                ],
              ),
              preferredSize: Size.fromHeight(5)),
          backgroundColor: lesCols[5],
          centerTitle: true,
          elevation: 0,
          title: Text(
            "Sign up",
            style: TextStyle(
                color: lesCols[6],
                fontFamily: "MADE Evolve Sans Regular EVO",
                fontSize: 30,
                fontWeight: FontWeight.bold),
          ),
          actions: <Widget>[
            Container(
              decoration: ShapeDecoration(
                shape: const StadiumBorder(),
                gradient: LinearGradient(
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                  colors: [lesCols[3], lesCols[0]],
                ),
              ),
              child: MaterialButton(
                materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
                shape: StadiumBorder(),
                child: Text(
                  "Next",
                  style: TextStyle(
                      color: lesCols[5],
                      fontFamily: "Manrope Light",
                      fontWeight: FontWeight.bold,
                      fontSize: 15),
                ),
                onPressed: () {
                  print("Yes, next");
                },
              ),
            ),
          ],
          leading: IconButton(
            icon: Icon(Icons.chevron_left, color: lesCols[6], size: 40),
            onPressed: () async {
              try {
                _firerepo.sadlySignOut();
              } catch (e) {
                print(e);
              }
              Navigator.pushReplacement(context,
                  MaterialPageRoute(builder: (context) {
                return Welcoming();
              }));
            },
          ),
        ),
        body: noConnecting
            ? Padding(
                padding: EdgeInsets.symmetric(horizontal: 20),
                child: SingleChildScrollView(
                  child: Column(
                    children: <Widget>[
                      SizedBox(height: 30),
                      TextButton(
                        onPressed: () async {
                          file =
                              // ignore: deprecated_member_use
                              await ImagePicker()
                                  .getImage(source: ImageSource.gallery);
                          setState(() {
                            fileimagen = FileImage(File(file.path));
                            profpicState = Icon(null);
                          });
                        },
                        child: Container(
                          decoration: BoxDecoration(
                            shape: BoxShape.circle,
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey.withOpacity(1),
                                spreadRadius: 3,
                                blurRadius: 20,
                                offset:
                                    Offset(0, 3), // changes position of shadow
                              ),
                            ],
                          ),
                          child: CircleAvatar(
                            radius: 75,
                            backgroundColor: lesCols[6],
                            child: CircleAvatar(
                              radius: 70,
                              backgroundColor: lesCols[5],
                              backgroundImage: fileimagen,
                              child: profpicState,
                            ),
                          ),
                        ),
                      ),
                      SizedBox(height: 25),
                      usernamewidg,
                      SizedBox(height: 10),
                      emailwidg,
                      SizedBox(height: 35),
                      paswidg,
                      SizedBox(height: 10),
                      pas2widg,
                      SizedBox(height: 30),
                      Center(
                        child: TextButton(
                          style: TextButton.styleFrom(
                            shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(40)),
                          ),
                          onPressed: () async {
                            bool isSignedIn = await _googleSignIn.isSignedIn();
                            setState(() {
                              noConnecting = false;
                              print("Wait What?");
                            });
                            try {
                              if (isSignedIn) {
                                _googleSignIn.signOut();
                                setState(() {
                                  noConnecting = true;
                                  rad = 15;
                                  goog = "images/googleicon.png";
                                  fileimagen = null;
                                  profpicState = Icon(
                                    Icons.add,
                                    color: lesCols[6],
                                    size: 50,
                                  );
                                  usernamewidg = TheTextpls(
                                    title: "Give yourself a cool Username",
                                    txtcol: lesCols[0],
                                    fillcol: lesCols[4],
                                    onPressed: (value) {
                                      username = _apprepo.trimstr(value);
                                    },
                                  );
                                  emailwidg = TheTextpls(
                                    title:
                                        "Enter your email, don't worry we won't spam",
                                    txtcol: lesCols[0],
                                    fillcol: lesCols[4],
                                    onPressed: (value) {
                                      email = _apprepo.trimstr(value);
                                    },
                                  );
                                  paswidg = TheTextpls(
                                    title:
                                        "This text field is asking for your password",
                                    txtcol: lesCols[0],
                                    fillcol: lesCols[4],
                                    obstxt: true,
                                    onPressed: (value) {
                                      pas = value;
                                    },
                                  );
                                  pas2widg = TheTextpls(
                                    title:
                                        "I'm asking for it again, hope you don´t mind :)",
                                    txtcol: lesCols[0],
                                    fillcol: Colors.yellowAccent[700],
                                    obstxt: true,
                                    onPressed: (value) {
                                      pas2 = value;
                                    },
                                  );
                                });
                              } else {
                                _googleSignIn.signOut();
                                await _googleSignIn.signIn();
                                if (_googleSignIn.currentUser.email != null) {
                                  setState(() {
                                    noConnecting = true;
                                    rad = 20;
                                    goog = "images/googicon.png";
                                    email = _googleSignIn.currentUser.email;
                                    fileimagen = NetworkImage(
                                        _googleSignIn.currentUser.photoUrl);
                                    profpicState = Icon(null);
                                    usernamewidg = TheTextpls(
                                      title: _googleSignIn
                                              .currentUser.displayName
                                              .split(" ")[0] +
                                          " try available usernames",
                                      onPressed: (value) {
                                        username = _apprepo.trimstr(value);
                                        print(username);
                                      },
                                    );
                                    emailwidg = TheTextpls(
                                      txtcol: Colors.grey[600],
                                      fillcol: Colors.grey[200],
                                      title: _googleSignIn.currentUser.email,
                                      entertxt: false,
                                    );
                                    paswidg = SizedBox(height: 1);
                                    pas2widg = SizedBox(height: 1);
                                  });
                                }
                              }
                            } catch (e) {
                              setState(() {
                                noConnecting = true;
                              });
                              print("Hi somehow got here... 3");
                              print(e);
                            }
                          },
                          child: CircleAvatar(
                            radius: rad,
                            backgroundColor: Colors.transparent,
                            backgroundImage: AssetImage(goog),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              )
            : Center(
                child: CircularProgressIndicator(
                  valueColor: AlwaysStoppedAnimation<Color>(lesCols[1]),
                  backgroundColor: Colors.yellowAccent[700],
                ),
              ),
      ),
    );
  }
}
