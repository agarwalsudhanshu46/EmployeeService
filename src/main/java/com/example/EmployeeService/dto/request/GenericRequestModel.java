package com.example.EmployeeService.dto.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class GenericRequestModel {
	
   private Map<String, Object> requestDetails = new HashMap<>();
    
   //private Object ob  = new Object();
   

    @JsonAnyGetter
    public Map<String, Object> getRequestDetails() {
	return requestDetails;
    }
    @JsonAnySetter
    public void setRequestDetails(String name, Object value) {
	this.requestDetails.put(name, value);
    }
/*    @JsonAnyGetter
	public Object getOb() {
		return ob;
	}
	@JsonAnySetter
	public void setOb(Object ob) {
		this.ob = ob;
	}  */

}
