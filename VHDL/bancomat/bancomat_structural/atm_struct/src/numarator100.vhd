library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity num_dir is
port(
	clk, en:  in std_logic;
	q: out std_logic_vector (3 downto 0)); 
end num_dir;

architecture arh of num_dir is
begin
process(clk, en) 
variable m: std_logic_vector (3 downto 0):="0000";
begin	 
if (en='1')	then 
	--m:="0000";
	if (CLK = '1' and CLK'event) then 
		 if(m<"1111") then 
			m:=(m+'1');
		else
			m:=m;	
		end if;
	end if;	
q<=m; 
elsif(en='0')then
	m:="0000"; 
	
end if;
end process;
end arh;