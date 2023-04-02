package com.info.footballlive.rest

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.info.footballlive.rest.model.League
import org.json.JSONObject

import java.io.IOException

class ItemTypeAdapterFactory : TypeAdapterFactory {

    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T> {

        val delegate = gson.getDelegateAdapter(this, type)
        val elementAdapter = gson.getAdapter(JsonElement::class.java)

        return object : TypeAdapter<T>() {
            override fun write(out: JsonWriter, value: T) {
                delegate.write(out, value)
            }

            override fun read(reader: JsonReader): T {

                var jsonElement = elementAdapter.read(reader)
                if (jsonElement.isJsonObject) {
                    val jsonObject = jsonElement.asJsonObject

                    if (jsonObject.has("api")) {
                        val apiObject = jsonObject.get("api").asJsonObject

                        val keys = apiObject.keySet()
                        val iterator = keys.iterator()
                        if (iterator.hasNext()) {
                            val firstKey = iterator.next()

                            if (iterator.hasNext()) {
                                val secondKey = iterator.next()
                                val secondObject = apiObject.get(secondKey)

                                return delegate.fromJsonTree(secondObject)
                            }
                        }
                    }
                }

                return delegate.fromJsonTree(jsonElement)
            }
        }
    }
}