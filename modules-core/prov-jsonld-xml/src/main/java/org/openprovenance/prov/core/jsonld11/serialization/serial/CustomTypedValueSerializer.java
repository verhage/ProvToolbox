package org.openprovenance.prov.core.jsonld11.serialization.serial;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.log4j.Logger;
import org.openprovenance.prov.core.jsonld11.serialization.Constants;
import org.openprovenance.prov.vanilla.LangString;
import org.openprovenance.prov.vanilla.ProvFactory;
import org.openprovenance.prov.vanilla.TypedValue;
import org.openprovenance.prov.model.QualifiedName;

import java.io.IOException;


public class CustomTypedValueSerializer extends StdSerializer<TypedValue> implements Constants {
   // private static Logger logger = Logger.getLogger(CustomTypedValueSerializer.class);

    static final QualifiedName QUALIFIED_NAME_XSD_STRING = ProvFactory.getFactory().getName().XSD_STRING;

    protected CustomTypedValueSerializer() {
        super(TypedValue.class);
    }

    protected CustomTypedValueSerializer(Class<TypedValue> t) {
        super(t);
    }
    @Override
    public void serialize(TypedValue attr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String s=null;
        if ((attr.getValue() instanceof LangString) &&
                ((LangString)attr.getValue()).getLang()==null &&
                (QUALIFIED_NAME_XSD_STRING.equals(attr.getType()))) {
            //jsonGenerator.writeString(((LangString)attr.getValue()).getValue().toString());

            //jsonGenerator.writeObject(attr.getValue());

            LangString me=(LangString)attr.getValue();
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.PROPERTY_STRING_VALUE, me.getValue());
            jsonGenerator.writeEndObject();

        } else if ((attr.getValue() instanceof String) &&
                (QUALIFIED_NAME_XSD_STRING.equals(attr.getType()))) {
            throw new UnsupportedOperationException("should never be here");
            //jsonGenerator.writeString((String)attr.getValue());
        } else if ((attr.getValue() instanceof LangString) &&
                ((LangString)attr.getValue()).getLang()!=null ) {

            //logger.info("serializeing here ?????????");
            LangString me=(LangString)attr.getValue();
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.PROPERTY_STRING_VALUE, me.getValue());
            jsonGenerator.writeStringField(Constants.PROPERTY_STRING_LANG,  me.getLang());
            jsonGenerator.writeEndObject();


            //jsonGenerator.getCodec().writeValue(jsonGenerator,attr.getValue()); // Luc,mongojack?


           // jsonGenerator.writeObject(attr.getValue());
        } else if ((attr.getValue() instanceof QualifiedName)  ) {
            jsonGenerator.writeObject(prnt((QualifiedName)attr.getValue()));
        } else {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(PROPERTY_AT_TYPE, prnt(attr.getType()));
            serializeValue(PROPERTY_AT_VALUE, attr.getValue(), jsonGenerator, serializerProvider);
            jsonGenerator.writeEndObject();
        }


    }

    private void serializeValue(String fieldName, Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (value instanceof String) {
            jsonGenerator.writeStringField (fieldName, (String)value);
            return;
        }
        if (value instanceof QualifiedName) {
            jsonGenerator.writeStringField (fieldName, prnt((QualifiedName)value));
            return;
        }
        if (value instanceof LangString) {
            jsonGenerator.writeFieldName(fieldName);
            jsonGenerator.writeObject(value);
            return;
        }
        throw new UnsupportedOperationException("unknown value type " + value);
    }

    private String prnt(QualifiedName qn) {
        return qn.getPrefix() + ":" + qn.getLocalPart();
    }
}