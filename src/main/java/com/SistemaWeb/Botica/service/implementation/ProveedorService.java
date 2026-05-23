package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Proveedor;
import com.SistemaWeb.Botica.repository.IProveedorRepository;
import com.SistemaWeb.Botica.service.IProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorService implements IProveedorService {
    private final IProveedorRepository repo;

    @Override 
    protected IGenericRepository<Proveedor,Integer> getRepo(){
        return repo;
    }

    /*
    @Override
    public Proveedor save(Proveedor proveedor) throws Exception {
        return repo.save(proveedor);
    }

    @Override
    public Proveedor update(Proveedor proveedor, Integer id) throws Exception {
        // Verificar existencia
        Proveedor existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + id));
        // Actualizar campos (excepto id)
        existing.setRazonSocial(proveedor.getRazonSocial());
        existing.setRuc(proveedor.getRuc());
        existing.setDireccion(proveedor.getDireccion());
        existing.setTelefono(proveedor.getTelefono());
        existing.setEmail(proveedor.getEmail());
        existing.setEstado(proveedor.getEstado());
        return repo.save(existing);
    }

    @Override
    public List<Proveedor> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Proveedor findById(Integer id) throws Exception {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        Proveedor proveedor = findById(id); // lanza excepción si no existe
        repo.delete(proveedor);
    }*/
}