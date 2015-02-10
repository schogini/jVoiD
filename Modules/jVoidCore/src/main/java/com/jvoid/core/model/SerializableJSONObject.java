package com.jvoid.core.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class SerializableJSONObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private transient JSONObject jsonObject;

    public SerializableJSONObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public SerializableJSONObject(SerializableJSONObject user) {
    	 this.jsonObject = user.getJSONObject();
	}

	public JSONObject getJSONObject() {
        return jsonObject;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(jsonObject.toString());
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException, JSONException {
        ois.defaultReadObject();
        jsonObject = new JSONObject((String) ois.readObject());
    }

}
