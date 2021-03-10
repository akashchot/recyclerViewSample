package com.adda.addatest.net

import com.adda.addatest.model.User
import com.adda.addatest.model.Users
import com.google.gson.*
import java.lang.reflect.Type

class UsersDeserializer : JsonDeserializer<Users> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Users {
        val users = Users()
        if (json.isJsonObject) {
            for ((key, value) in json.asJsonObject
                .entrySet()) {
                if (key == "code") {
                    users.status = value.asString
                } else if (key == "data") {
                    val jsonArray = value.asJsonArray
                    for (i in 0 until jsonArray.size()) {
                        val db =
                            Gson().fromJson(
                                jsonArray[i].asJsonObject.toString(),
                                User::class.java
                            )
                        users.addUser(db)
                    }
                }
            }
        }
        return users
    }
}