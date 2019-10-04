package com.amt.time_tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login implements Serializable {


    private static final long serialVersionUID = -7334590009839977479L;

    private String username;

    private String password;

}
