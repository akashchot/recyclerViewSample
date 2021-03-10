package com.adda.addatest.net;

import android.util.Log;

import com.adda.addatest.model.User;
import com.adda.addatest.model.Users;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;

public class UsersDeserializer implements JsonDeserializer<Users> {
    @Override
    public Users deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Users users = new Users();
        if (json.isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                if (entry.getKey().equals("code")) {
                    users.setStatus(entry.getValue().getAsString());
                } else if (entry.getKey().equals("data")) {
                    JsonArray jsonArray = entry.getValue().getAsJsonArray();
                    for (int i = 0 ; i<jsonArray.size();i++) {
                        User db = new Gson().fromJson(jsonArray.get(i).getAsJsonObject().toString(),User.class);
                        users.addUser(db);
                    }
                }
            }
        }
        return users;
    }
}
