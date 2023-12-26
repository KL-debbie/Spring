package org.doit.ik.domain;

import lombok.Data;

@Data
public class BookDTO {
   
   private String seq;
   private String title;
   private String link;
   private String description;
   private String image;
   private String author;
   private String discount;
   private String publisher;
   private String isbn;
   private String pubdate;
   
}