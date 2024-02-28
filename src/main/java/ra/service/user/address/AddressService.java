package ra.service.user.address;

import ra.model.dto.request.AddressRequest;
import ra.model.entity.Address;

import java.util.List;

public interface AddressService {
    Address save(AddressRequest addressRequest);
    List<Address> getByAddress(Long id);
    Address findById(Long id);
}
