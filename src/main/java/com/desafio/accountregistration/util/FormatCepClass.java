package com.desafio.accountregistration.util;

import com.desafio.accountregistration.core.exception.CustomInvalidArgumentException;

public class FormatCepClass {
    
    public static String FormatCep(String cep)
    {
        if (cep.length() != 8)
        {
          throw new CustomInvalidArgumentException("Informe o CEP corretamente contendo apenas 8 digitos.");
        }

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(cep.substring(0, 5));
        sBuilder.append("-");
        sBuilder.append(cep.substring(5, 8));        

        return sBuilder.toString();
    }

}
