package br.com.academia.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE"),
	PROFESSOR(3, "ROLE_PROFESSOR");
	
	private int codigo;
	private String descricao;
	
	private Perfil(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer codigo) 
	{
		if(codigo == null)
		{
			return null;
		}
		
		for(Perfil perfil: Perfil.values())
		{
			if(codigo.equals(perfil.getCodigo()))
			{
				return perfil;
			}
		}
		
		throw new IllegalArgumentException("Código inválido"+ codigo);
	}

}
