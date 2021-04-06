library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use std.standard.all;
entity muxcomanda is
	port(ok:in std_logic;
	comandain:in std_logic_vector (1 downto 0);
	comandaout: out std_logic_vector (1 downto 0));
end entity;

architecture Behavioral of muxcomanda is
type dmux is array (0 to 3) of std_logic_vector (1 downto 0);
signal B_dmux: dmux:=("00","01","10","11");
begin 
	process(comandain)									
	variable comandaout1: std_logic_vector(1 downto 0);
	begin
	if comandain=B_dmux(0) then
	comandaout1:="00";  
	elsif comandain=B_dmux(1) then
	comandaout1:="01";   
	elsif comandain=B_dmux(2) then
	comandaout1:="10";
	elsif comandain=B_dmux(3) then
	comandaout1:="11";	
	end if;		
comandaout<=comandaout1;
end process;
end architecture;

	