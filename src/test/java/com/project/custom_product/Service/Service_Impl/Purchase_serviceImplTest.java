package com.project.custom_product.Service.Service_Impl;

import com.project.custom_product.Respository.purchase_repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Purchase_serviceImplTest {

    @Mock
    private purchase_repository purchase_repos;

    @InjectMocks
    private Purchase_serviceImpl purchase_service;

    @BeforeEach
    void setUp() {


    }
}