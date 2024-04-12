package com.example.minicraftserver.domain.market.domain.repository

import com.example.minicraftserver.domain.market.domain.Market
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MarketRepository : CrudRepository<Market, Long>
