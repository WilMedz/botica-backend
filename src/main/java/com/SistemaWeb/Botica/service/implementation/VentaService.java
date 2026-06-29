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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Venta> listPage(Pageable pageable) {
        return repo.findAll(pageable);
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

    @Override
    @Transactional
    public Venta update(Venta t, Integer id) {
        Venta ventaAnterior = repo.findById(id).orElse(null);
        if (ventaAnterior != null && ventaAnterior.getDetalles() != null) {
            for (DetalleVenta detAnterior : ventaAnterior.getDetalles()) {
                ajustarStock(detAnterior, true, id);
            }
        }
        t.setIdVenta(id);
        Venta actualizada = repo.save(t);
        if (actualizada.getDetalles() != null) {
            for (DetalleVenta detNuevo : actualizada.getDetalles()) {
                ajustarStock(detNuevo, false, id);
            }
        }
        return actualizada;
    }

    private void ajustarStock(DetalleVenta detalle, boolean devolver, Integer idVenta) {
        if (detalle.getProducto() == null || detalle.getProducto().getIdProducto() == null) return;
        Producto prod = productoRepo.findById(detalle.getProducto().getIdProducto()).orElse(null);
        if (prod == null) return;
        int stockAnterior = prod.getStock();
        int stockNuevo = devolver ? stockAnterior + detalle.getCantidad() : stockAnterior - detalle.getCantidad();
        prod.setStock(stockNuevo);
        productoRepo.save(prod);
        MovimientoInventario mov = new MovimientoInventario();
        mov.setProducto(prod);
        mov.setTipo(devolver ? "ENTRADA" : "SALIDA");
        mov.setCantidad(detalle.getCantidad());
        mov.setStockAnterior(stockAnterior);
        mov.setStockNuevo(stockNuevo);
        mov.setMotivo(devolver ? "Edición de Venta #" + idVenta + " (reverso)" : "Edición de Venta #" + idVenta);
        mov.setFecha(LocalDateTime.now());
        movimientoRepo.save(mov);
    }
}