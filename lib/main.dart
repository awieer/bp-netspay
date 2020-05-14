import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

const CHANNEL = "com.berrypay.bps.channel";
const KEY_NATIVE = "showNativeView";

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Bridging Demo - Flutter Layer'),
    );
  }
}

class MyHomePage extends StatelessWidget {
  static const platform = const MethodChannel(CHANNEL);
  final String title;

  MyHomePage({Key key, this.title}) : super(key: key) {
    platform.setMethodCallHandler(_handleMethod);
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text(title),
      ),
      body: new Center(
        child: new Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            new RaisedButton(
              child: new Text('Open NetsPay'),
              onPressed: _showNativeView,
            ),
          ],
        ),
      ),
    );
  }

  Future<Null> _showNativeView() async {
    final String txnRef = "BP12345609812312";
    await platform.invokeMethod(KEY_NATIVE, ["1000", txnRef]);
  }

  Future<dynamic> _handleMethod(MethodCall call) async {
    switch (call.method) {
      case "message":
        debugPrint(call.arguments);
        return new Future.value("");
    }
  }
}
