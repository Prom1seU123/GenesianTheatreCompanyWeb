package com.au.GenesianTheatreCompany.service;

import org.springframework.stereotype.Service;

@Service
public interface LoggingService{
    void writeLog(String message);
}
