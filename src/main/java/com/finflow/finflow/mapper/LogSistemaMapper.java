package com.finflow.finflow.mapper;

import com.finflow.finflow.dto.LogSistemaResponse;
import com.finflow.finflow.model.LogSistema;

public class LogSistemaMapper {

    public static LogSistemaResponse toResponse(LogSistema log) {
        LogSistemaResponse res = new LogSistemaResponse();

        res.setId(log.getId());
        res.setTabela(log.getTabela());
        res.setRegistroId(log.getRegistroId());
        res.setAcao(log.getAcao());
        res.setDescricao(log.getDescricao());
        res.setDataHora(log.getDataHora());

        return res;
    }
}