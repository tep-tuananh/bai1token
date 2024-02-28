package ra.service.user.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.AddressRequest;
import ra.model.entity.Address;
import ra.repository.AddressRepository;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address findById(Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        return address;
    }

    @Override
    public List<Address> getByAddress(Long id) {
        List<Address> addresses = addressRepository.findAddressByUser_Id(id);
        return addresses;
    }

    @Override
    public Address save(AddressRequest addressRequest) {
        Address address = addressRequest.add(addressRequest);
      return addressRepository.save(address);
    }
}
