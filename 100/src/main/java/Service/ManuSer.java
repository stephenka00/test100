package Service;

import Entity.Manufacturer;
import Repository.ManufacturerRepository;
import Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ManuSer {
    @Autowired

    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Optional<Manufacturer> getManufacturerById(Long id) {
        return manufacturerRepository.findById(id);
    }

    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public Manufacturer updateManufacturer(Long id, Manufacturer manufacturerDetails) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found for this id :: " + id));

        manufacturer.setName(manufacturerDetails.getName());
        manufacturer.setOrigin(manufacturerDetails.getOrigin());
        manufacturer.setDescription(manufacturerDetails.getDescription());

        return manufacturerRepository.save(manufacturer);
    }

    public void deleteManufacturer(Long id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found for this id :: " + id));

        if (!manufacturer.getProducts().isEmpty()) {
            throw new IllegalArgumentException("Cannot delete manufacturer with products.");
        }

        manufacturerRepository.delete(manufacturer);
    }
}
