package com.ridwanadit.stashbox.stashjson;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class StashJSONParser {
	static StashArray json;
	static ObjectMapper mapper = new ObjectMapper();

	public static StashArray parse(InputStream stash) {
		try {
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			json = mapper.readValue(stash, StashArray.class);
		} catch (JsonParseException e) {e.printStackTrace();
		} catch (JsonMappingException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}
		return json;
	}
}
