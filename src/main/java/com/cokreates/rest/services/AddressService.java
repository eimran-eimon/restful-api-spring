package com.cokreates.rest.services;

import com.cokreates.rest.common.AddressDTO;
import com.cokreates.rest.model.response.AddressesRest;

import java.util.List;

public interface AddressService {
	List<AddressDTO> getAddresses(String id);
}
