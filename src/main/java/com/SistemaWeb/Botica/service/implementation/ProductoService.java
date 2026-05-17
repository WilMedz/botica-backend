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

    
    private ProductoDTO toDTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(p.getIdProducto());
        dto.setNombre(p.getNombre());
        dto.setCodigoBarra(p.getCodigoBarra());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecioCompra(p.getPrecioCompra());
        dto.setPrecioVenta(p.getPrecioVenta());
        dto.setStock(p.getStock());
        dto.setStockMinimo(p.getStockMinimo());
        dto.setEstado(p.getEstado());
        dto.setFechaVencimiento(p.getFechaVencimiento());

        if (p.getCategoria() != null) {
            dto.setIdCategoria(p.getCategoria().getIdCategoria());
            dto.setNombreCategoria(p.getCategoria().getNombre());
        }

        if (p.getProveedor() != null) {
            dto.setIdProveedor(p.getProveedor().getIdProveedor());
            dto.setRazonSocialProveedor(p.getProveedor().getRazonSocial());
        }

        return dto;
    }

    
    private Producto toEntity(ProductoDTO dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setCodigoBarra(dto.getCodigoBarra());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecioCompra(dto.getPrecioCompra());
        p.setPrecioVenta(dto.getPrecioVenta());
        p.setStock(dto.getStock());
        p.setStockMinimo(dto.getStockMinimo());
        p.setEstado(dto.getEstado());
        p.setFechaVencimiento(dto.getFechaVencimiento());

        Categoria cat = new Categoria();
        cat.setIdCategoria(dto.getIdCategoria());
        p.setCategoria(cat);

        if (dto.getIdProveedor() != null) {
            Proveedor prov = new Proveedor();
            prov.setIdProveedor(dto.getIdProveedor());
            p.setProveedor(prov);
        }

        return p;
    }

    @Override
    public ProductoDTO save(ProductoDTO dto) throws Exception {
        Producto producto = toEntity(dto);
        return toDTO(repo.save(producto));
    }

    @Override
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
    }
}