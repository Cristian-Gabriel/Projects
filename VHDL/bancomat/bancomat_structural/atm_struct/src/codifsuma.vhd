library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use std.standard.all;

entity sumacodif is
	port(suma: in integer;
	zeci,sute,mii: out std_logic_vector(3 downto 0));
end entity;
architecture Behavioral of sumacodif is	   
signal z,s,m:integer:=0;
begin
	process(suma,z,s,m)
	variable sumatest:integer;

		--variable z,s,m:integer:=0; 
		begin
				sumatest:=suma;
		if(sumatest>=0) then
		sumatest:=sumatest/10;
		z<=sumatest mod 10;
		sumatest:=sumatest/10;
		s<=sumatest mod 10;
		sumatest:=sumatest/10;
		m<=sumatest;
	end if;
	zeci<=conv_std_logic_vector(z,4);
	sute<=conv_std_logic_vector(s,4);
	mii<=conv_std_logic_vector(m,4);
	end process;
end architecture;