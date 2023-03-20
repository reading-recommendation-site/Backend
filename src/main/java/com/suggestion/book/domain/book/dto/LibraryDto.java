package com.suggestion.book.domain.book.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Library;

import java.util.List;

@Getter
@Setter
public class LibraryDto {
    public Library lib;

    public static class Library{
        public String libCode;
        public String libName;
        public String address;
        public String tel;
        public String fax;
        public String latitude;
        public String longitude;
        public String homepage;
        public String closed;
        public String operatingTime;
    }
}

