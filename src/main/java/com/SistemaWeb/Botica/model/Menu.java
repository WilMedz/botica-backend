package com.SistemaWeb.Botica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menus")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idMenu;

    @Column(nullable = false, length = 50)
    private String nombre; 

    @Column(nullable = false, length = 50)
    private String icono; 

    @Column(nullable = false, length = 100)
    private String url; 

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "menu_roles", 
        joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "idMenu"),
        inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol")
    )
    private List<Rol> roles;
}
