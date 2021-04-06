library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use std.standard.all;
entity sumabancnote is
	port(comanda: in std_logic_vector(1 downto 0);
	a1,b1,c1:in integer;
		a2,b2,c2: in integer;
	output1,output2,output3: out integer);
end entity;

architecture Behavioral of sumabancnote is
begin			
process(a1,b1,c1,a2,b2,c2)
variable output1v:integer:=0;
variable output2v:integer:=0;
variable output3v:integer:=0;
begin			 
	if(comanda="10") then
	output1v:=a1+a2;
	output2v:=b1+b2;
	output3v:=c1+c2;
	output1<=output1v;
	output2<=output2v;
	output3<=output3v;
	end if;

end process;	
end architecture;
