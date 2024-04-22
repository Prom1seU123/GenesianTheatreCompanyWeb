package com.au.GenesianTheatreCompany.service;

import com.au.GenesianTheatreCompany.Common.Result;

public interface EmailService {
    Result sendEmail(String email, String subject, String content);
}

