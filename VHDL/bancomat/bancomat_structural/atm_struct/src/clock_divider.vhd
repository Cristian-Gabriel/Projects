LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.std_logic_unsigned.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity clock_divider is
	port(clk: in std_logic;
	r: in std_logic;
	clk_out: out std_logic);	
end clock_divider;

architecture Behavioral of clock_divider is
signal divider: std_logic_vector(3 downto 0);
begin
	process(clk,r)
	begin 
		if r='1' then
		divider <= "0000";
	elsif rising_edge(clk) then
		divider <= divider + '1';
	end if;
	end process;
	clk_out<=divider(2); 
	
	end Behavioral;