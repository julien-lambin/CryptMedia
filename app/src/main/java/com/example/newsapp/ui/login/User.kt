package com.example.newsapp.ui.login
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmField
import io.realm.annotations.Required

open class User(
    @PrimaryKey @RealmField("_id") var id: String = "",
    var _partition: String = "",
    var name: String = ""
): RealmObject()