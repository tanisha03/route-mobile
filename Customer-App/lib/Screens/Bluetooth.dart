import 'package:app2/constants.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class Bluetooth extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: Text(('live updates')),
          automaticallyImplyLeading: true,
          leading: IconButton(
              icon: Icon(Icons.arrow_back),
              onPressed: () => Navigator.pop(context, false))),
      body: UserList(),
    );
  }
}

class UserList extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _UserListState();
  }
}

class _UserListState extends State<UserList> {
  List<dynamic> _users = [];

  void fetchUsers() async {
    var apiUrl = URL + "/merchant/bluetooth/offer/";
    var result = await http.get(apiUrl);
    setState(() {
      _users = json.decode(result.body)['responseObject'];
      debugPrint(_users.toList().toString());
    });
  }

  Widget _buildList() {
    return _users.length != 0
        ? RefreshIndicator(
            child: ListView.builder(
                padding: EdgeInsets.all(8.0),
                itemCount: _users.length,
                itemBuilder: (BuildContext context, int index) {
                  return Card(
                    child: Column(
                      children: <Widget>[
                        GestureDetector(
                            onTap: () {},
                            child: ListTile(
                              leading: CircleAvatar(
                                  radius: 30,
                                  backgroundImage: NetworkImage(
                                      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNgeHA0SK41ZMn3S_xdxmhf144t3NH1ZvGfA&usqp=CAU")),
                              title: Text(_users[index]['title']),
                              subtitle: Text(_users[index]['category'] +
                                  ":  " +
                                  _users[index]['description']),
                            ))
                      ],
                    ),
                  );
                }),
            onRefresh: _getData,
          )
        : Center(child: CircularProgressIndicator());
  }

  Future<void> _getData() async {
    setState(() {
      fetchUsers();
    });
  }

  @override
  void initState() {
    super.initState();
    fetchUsers();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: _buildList(),
      ),
    );
  }
}
