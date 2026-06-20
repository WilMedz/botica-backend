package com.SistemaWeb.Botica.service.implementation;

import com.SistemaWeb.Botica.model.Venta;
import com.SistemaWeb.Botica.model.DetalleVenta;
import com.SistemaWeb.Botica.model.Producto;
import com.SistemaWeb.Botica.model.MovimientoInventario;
import com.SistemaWeb.Botica.repository.IGenericRepository;
import com.SistemaWeb.Botica.repository.IVentaRepository;
import com.SistemaWeb.Botica.repository.IProductoRepository;
import com.SistemaWeb.Botica.repository.IMovimientoInventarioRepository;
import com.SistemaWeb.Botica.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VentaService extends GenericService<Venta, Integer> implements IVentaService {
    private final IVentaRepository repo;
    private final IProductoRepository productoRepo;
    private final IMovimientoInventarioRepository movimientoRepo;
    
    @Override
    protected IGenericRepository<Venta, Integer> getRepo() {
        return repo;
    }

    @Override
    @Transactional
    public Venta save(Venta t) {
        
        Venta saved = repo.save(t);
        
        if (saved.getDetalles() != null) {
            for (DetalleVenta det : saved.getDetalles()) {
                if (det.getProducto() != null && det.getProducto().getIdProducto() != null) {
                    Producto prod = productoRepo.findById(det.getProducto().getIdProducto()).orElse(null);
                    if (prod != null) {
                        int stockAnterior = prod.getStock();
                        int stockNuevo = stockAnterior - det.getCantidad();
                        prod.setStock(stockNuevo);
                        productoRepo.save(prod);
                        
                        MovimientoInventario mov = new MovimientoInventario();
                        mov.setProducto(prod);
                        mov.setTipo("SALIDA");
                        mov.setCantidad(det.getCantidad());
                        mov.setStockAnterior(stockAnterior);
                        mov.setStockNuevo(stockNuevo);
                        mov.setMotivo("Venta #" + saved.getIdVenta());
                        mov.setFecha(LocalDateTime.now());
                        movimientoRepo.save(mov);
                    }
                }
            }
        }
        
        return saved;
    }
}