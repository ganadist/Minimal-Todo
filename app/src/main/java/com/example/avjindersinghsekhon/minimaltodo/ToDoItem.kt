package com.example.avjindersinghsekhon.minimaltodo

import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable
import java.util.Date
import java.util.UUID

data class ToDoItem(var toDoText: String = "Clean my room",
                    var hasReminder: Boolean = true,
                    var toDoDate: Date? = Date(),
                    var todoColor: Int = 0,
                    var identifier: UUID = UUID.randomUUID()
                    ) : Serializable {

    @Throws(JSONException::class)
    constructor(jsonObject: JSONObject):
        this(jsonObject.getString(TODOTEXT),
                jsonObject.getBoolean(TODOREMINDER),
                jsonObject.has(TODODATE).let {
                    Date(jsonObject.getLong(TODODATE))
                },
                jsonObject.getInt(TODOCOLOR),
                UUID.fromString(jsonObject.getString(TODOIDENTIFIER))) {
    }

    @Throws(JSONException::class)
    fun toJSON(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(TODOTEXT, toDoText)
        jsonObject.put(TODOREMINDER, hasReminder)
        //        jsonObject.put(TODOLASTEDITED, mLastEdited.getTime());
        if (toDoDate != null) {
            jsonObject.put(TODODATE, toDoDate!!.time)
        }
        jsonObject.put(TODOCOLOR, todoColor)
        jsonObject.put(TODOIDENTIFIER, identifier.toString())

        return jsonObject
    }


    // for legacy
    constructor(toDoText: String,
                hasReminder: Boolean,
                toDoDate: Date?):
            this(toDoText, hasReminder, toDoDate, 0) {
    }

    // for legacy
    fun hasReminder(): Boolean {
        return hasReminder
    }

    companion object {
        private val TODOTEXT = "todotext"
        private val TODOREMINDER = "todoreminder"
        //    private static final String TODOLASTEDITED = "todolastedited";
        private val TODOCOLOR = "todocolor"
        private val TODODATE = "tododate"
        private val TODOIDENTIFIER = "todoidentifier"
    }
}