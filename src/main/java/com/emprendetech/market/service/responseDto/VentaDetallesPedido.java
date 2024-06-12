package com.emprendetech.market.service.responseDto;

import java.util.ArrayList;
import java.util.List;

import com.emprendetech.market.service.requestDto.AltaDetallesVentaDto;
import com.emprendetech.market.service.requestDto.AltaVentasDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VentaDetallesPedido {

	List<AltaVentasDto> encabezado = new ArrayList<AltaVentasDto>();
	List<AltaDetallesVentaDto> detalles = new ArrayList<AltaDetallesVentaDto>();

	
}
