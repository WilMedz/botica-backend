package com.SistemaWeb.Botica.util;

import com.SistemaWeb.Botica.model.*;
import com.SistemaWeb.Botica.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IRolRepository rolRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ICategoriaRepository categoriaRepository;
    private final IProveedorRepository proveedorRepository;
    private final IClienteRepository clienteRepository;
    private final IProductoRepository productoRepository;
    private final IMenuRepository menuRepository;

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.count() == 0) {
            Rol admin = new Rol(null, "ADMINISTRADOR");
            Rol user = new Rol(null, "USUARIO");
            rolRepository.saveAll(Arrays.asList(admin, user));
            System.out.println("Roles insertados.");
        }

        if (usuarioRepository.count() == 0) {
            Rol adminRole = rolRepository.findAll().stream()
                    .filter(r -> r.getNombre().equalsIgnoreCase("ADMINISTRADOR"))
                    .findFirst()
                    .orElse(null);

            Rol userRole = rolRepository.findAll().stream()
                    .filter(r -> r.getNombre().equalsIgnoreCase("USUARIO"))
                    .findFirst()
                    .orElse(null);

            if (adminRole != null) {
                // Usando un hash BCrypt para la contraseña "admin"
                Usuario admin = new Usuario(
                        null,
                        "Admin",
                        "Botica",
                        "admin",
                        "$2a$12$6YTZGNkwi.IoFCtoVP/rIusLpekDNzFgOuy/dvzYMEtk5IZG.T2o6", // BCrypt para la contraseña: admin
                        "admin@botica.com",
                        true,
                        adminRole
                );
                usuarioRepository.save(admin);
                System.out.println("Usuario de prueba insertado.");
            }

            if (userRole != null) {
                Usuario vendedor = new Usuario(
                        null,
                        "Carlos",
                        "Vendedor",
                        "vendedor",
                        "$2a$12$6YTZGNkwi.IoFCtoVP/rIusLpekDNzFgOuy/dvzYMEtk5IZG.T2o6", // mismo hash: contraseña "admin"
                        "vendedor@botica.com",
                        true,
                        userRole
                );
                usuarioRepository.save(vendedor);
                System.out.println("Usuario vendedor insertado.");
            }
        }

        if (categoriaRepository.count() == 0) {
            List<Categoria> categories = Arrays.asList(
                    new Categoria(null, "Antibióticos", "Medicamentos para combatir infecciones bacterianas", true),
                    new Categoria(null, "Analgésicos y Antiinflamatorios", "Alivio de dolores musculares, fiebre e inflamaciones", true),
                    new Categoria(null, "Cuidado de la Piel (Dermatología)", "Cremas, protectores solares y lociones para la piel", true),
                    new Categoria(null, "Suplementos y Vitaminas", "Complementos alimenticios y multivitamínicos", true),
                    new Categoria(null, "Cuidado del Bebé", "Pañales, fórmulas y productos de aseo para bebés", true),
                    new Categoria(null, "Cuidado Personal (Higiene)", "Shampoos, jabones, desodorantes y aseo general", true),
                    new Categoria(null, "Equipos y Dispositivos Médicos", "Tensiómetros, termómetros y otros dispositivos", true),
                    new Categoria(null, "Antialérgicos", "Medicamentos antihistamínicos para alergias", true),
                    new Categoria(null, "Salud Cardiovascular", "Medicamentos para el control de presión arterial y corazón", true),
                    new Categoria(null, "Pediatría", "Medicamentos y complementos pediátricos", true)
            );
            categoriaRepository.saveAll(categories);
            System.out.println("Categorías iniciales insertadas.");
        }

        if (proveedorRepository.count() == 0) {
            List<Proveedor> proveedores = Arrays.asList(
                    new Proveedor(null, "Droguería FarmaSalud S.A.C.", "20123456789", "Av. Los Libertadores 456, Lima", "01-4455667", "ventas@farmasalud.com", true),
                    new Proveedor(null, "Distribuidora Médica Universal", "20987654321", "Jr. Ica 789, Arequipa", "054-223344", "contacto@medicauniversal.com", true),
                    new Proveedor(null, "Corporación Química del Sur", "20556677889", "Av. Industrial 123, Trujillo", "044-332211", "corporacion@quimicasur.com", true)
            );
            proveedorRepository.saveAll(proveedores);
            System.out.println("Proveedores de prueba insertados.");
        }

        if (clienteRepository.count() == 0) {
            List<Cliente> clientes = Arrays.asList(
                    new Cliente(null, "Juan", "Perez", "12345678", "987654321", "juan.perez@gmail.com", true),
                    new Cliente(null, "Maria", "Rodriguez", "87654321", "912345678", "maria.rodriguez@gmail.com", true),
                    new Cliente(null, "Carlos", "Mendoza", "45678912", "954321678", "carlos.mendoza@gmail.com", true)
            );
            clienteRepository.saveAll(clientes);
            System.out.println("Clientes de prueba insertados.");
        }

        List<Categoria> savedCategories = categoriaRepository.findAll();
        List<Proveedor> savedProveedores = proveedorRepository.findAll();

        if (productoRepository.count() == 0 && !savedCategories.isEmpty()) {
            Categoria analgesicos = savedCategories.stream()
                    .filter(c -> c.getNombre().contains("Analgésicos"))
                    .findFirst()
                    .orElse(savedCategories.get(0));

            Categoria antibioticos = savedCategories.stream()
                    .filter(c -> c.getNombre().contains("Antibióticos"))
                    .findFirst()
                    .orElse(savedCategories.get(0));

            Categoria cuidadoPiel = savedCategories.stream()
                    .filter(c -> c.getNombre().contains("Cuidado de la Piel"))
                    .findFirst()
                    .orElse(savedCategories.get(0));

            Proveedor prov1 = savedProveedores.isEmpty() ? null : savedProveedores.get(0);
            Proveedor prov2 = savedProveedores.size() > 1 ? savedProveedores.get(1) : prov1;

            List<Producto> productos = Arrays.asList(
                    new Producto(null, "Paracetamol 500mg", "7750123456789", "Analgésico y antipirético para alivio de dolores leves y fiebre.", new BigDecimal("0.10"), new BigDecimal("0.50"), 100, 10, analgesicos, prov1, true, LocalDate.now().plusYears(2)),
                    new Producto(null, "Ibuprofeno 400mg", "7750123456790", "Antiinflamatorio no esteroideo para dolores musculares y de cabeza.", new BigDecimal("0.15"), new BigDecimal("0.70"), 150, 15, analgesicos, prov1, true, LocalDate.now().plusYears(2)),
                    new Producto(null, "Amoxicilina 500mg", "7750123456791", "Antibiótico de amplio espectro para infecciones bacterianas.", new BigDecimal("0.50"), new BigDecimal("2.50"), 80, 8, antibioticos, prov2, true, LocalDate.now().plusYears(1)),
                    new Producto(null, "Bloqueador Solar SPF 50+", "7750123456792", "Protector solar dermatológico de alta protección y rápida absorción.", new BigDecimal("15.00"), new BigDecimal("35.00"), 40, 5, cuidadoPiel, prov2, true, LocalDate.now().plusYears(3))
            );
            productoRepository.saveAll(productos);
            System.out.println("Productos de prueba insertados.");
        }

        List<Rol> roles = rolRepository.findAll();
        Rol adminRole2 = roles.stream().filter(r -> r.getNombre().equalsIgnoreCase("ADMINISTRADOR")).findFirst().orElse(null);
        Rol userRole2 = roles.stream().filter(r -> r.getNombre().equalsIgnoreCase("USUARIO")).findFirst().orElse(null);

        if (menuRepository.count() == 0 && adminRole2 != null && userRole2 != null) {
            Menu dashboard = new Menu(null, "Dashboard", "dashboard", "/pages/dashboard", Arrays.asList(adminRole2, userRole2));
            Menu categorias = new Menu(null, "Categorías", "category", "/pages/categorias", Arrays.asList(adminRole2, userRole2));
            Menu proveedores = new Menu(null, "Proveedores", "local_shipping", "/pages/proveedores", Arrays.asList(adminRole2));
            Menu productos = new Menu(null, "Productos", "medication", "/pages/productos", Arrays.asList(adminRole2, userRole2));
            Menu clientes = new Menu(null, "Clientes", "people", "/pages/clientes", Arrays.asList(adminRole2, userRole2));
            Menu ventas = new Menu(null, "Ventas", "point_of_sale", "/pages/ventas", Arrays.asList(adminRole2, userRole2));
            //Menu roles_ = new Menu(null, "Roles", "key", "/pages/roles", Arrays.asList(adminRole2));
            Menu usuarios = new Menu(null, "Usuarios", "person", "/pages/usuarios", Arrays.asList(adminRole2));

            menuRepository.saveAll(Arrays.asList(dashboard, categorias, proveedores, productos, clientes, ventas, usuarios));
            System.out.println("Menús insertados.");
        }
    }
}
