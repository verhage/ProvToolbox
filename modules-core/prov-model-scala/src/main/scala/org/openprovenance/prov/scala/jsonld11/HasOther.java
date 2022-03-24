package org.openprovenance.prov.scala.jsonld11;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.openprovenance.prov.model.Other;

import java.util.List;

public interface HasOther extends org.openprovenance.prov.model.HasOther {


    @JsonIgnore
    List<Other> getOther();




}
