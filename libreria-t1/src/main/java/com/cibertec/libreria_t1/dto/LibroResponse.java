package com.cibertec.libreria_t1.dto;

import java.math.BigDecimal;

public class LibroResponse {

    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private BigDecimal precio;
    private Integer stock;

    public LibroResponse() {}

    public LibroResponse(Long id, String isbn, String titulo, String autor, BigDecimal precio, Integer stock) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
