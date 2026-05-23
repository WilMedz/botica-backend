package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.dto.ProductoDTO;
import com.SistemaWeb.Botica.exception.ModelNotFoundException;
import com.SistemaWeb.Botica.model.Categoria;
import com.SistemaWeb.Botica.model.Producto;
import com.SistemaWeb.Botica.model.Proveedor;
import com.SistemaWeb.Botica.repository.IProductoRepository;
import com.SistemaWeb.Botica.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoService {
    private final IProductoRepository repo;

    @Override
    protected IGenericRepository<Producto,Integer> getRepo(){
        return repo;
    }

    /*@Override
    public ProductoDTO update(ProductoDTO dto, Integer id) throws Exception {
        repo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Producto no encontrado con id: " + id));
        Producto producto = toEntity(dto);
        producto.setIdProducto(id);
        return toDTO(repo.save(producto));
    }

    @Override
    public List<ProductoDTO> findAll() throws Exception {
        return repo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public ProductoDTO findById(Integer id) throws Exception {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Producto no encontrado con id: " + id));
        return toDTO(producto);
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Producto no encontrado con id: " + id));
        repo.deleteById(id);
    }*/
}