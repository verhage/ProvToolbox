package org.openprovenance.prov.core.json.serialization;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.openprovenance.prov.core.json.serialization.deserial.CustomAttributeSetDeserializer;
import org.openprovenance.prov.core.json.serialization.deserial.CustomBundleDeserializer;
import org.openprovenance.prov.core.json.serialization.deserial.CustomKindDeserializer;
import org.openprovenance.prov.core.json.serialization.deserial.CustomNamespaceDeserializer;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.exception.UncheckedException;
import org.openprovenance.prov.vanilla.Bundle;
import org.openprovenance.prov.vanilla.ProvFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

public class ProvDeserialiser implements org.openprovenance.prov.model.ProvDeserialiser{

    ProvFactory pf=new ProvFactory();

    private final ProvMixin provMixin = new ProvMixin();
    final ObjectMapper mapper = new ObjectMapper();


    public ProvDeserialiser() {
        customize(mapper);
    }


    public org.openprovenance.prov.model.Document deserialiseDocument (File serialised) throws IOException {
        return deserialiseDocument(new FileInputStream(serialised));
    }
    public org.openprovenance.prov.model.Document deserialiseDocument (InputStream in)  {

        SortedDocument doc= null;
        try {
            doc = mapper.readValue(in, SortedDocument.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedException(e);
        }

        return doc.toDocument(pf);

    }

    public void customize(ObjectMapper mapper) {
        SimpleModule module =
                new SimpleModule("CustomKindSerializer", new Version(1, 0, 0, null, null, null));

        module.addDeserializer(org.openprovenance.prov.model.StatementOrBundle.Kind.class, new CustomKindDeserializer());


        TypeFactory typeFactory = mapper.getTypeFactory();


        MapType mapType2 = typeFactory.constructMapType(HashMap.class, String.class, String.class);
        module.addDeserializer(Namespace.class, new CustomNamespaceDeserializer(mapType2));


        module.addDeserializer(Bundle.class, new CustomBundleDeserializer());

        CollectionType setType = typeFactory.constructCollectionType(Set.class, org.openprovenance.prov.model.Attribute.class);
        module.addDeserializer(Set.class,new CustomAttributeSetDeserializer(setType));

        provMixin.addProvMixin(mapper);

        mapper.registerModule(module);
    }

}
