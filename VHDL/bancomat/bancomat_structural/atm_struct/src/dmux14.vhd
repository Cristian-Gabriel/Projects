entity DEMUX1_to_4 is
	port( i,sel1,sel2:in bit; o0,o1,o2,o3:out bit);
end DEMUX1_to_4;
architecture DMUX14 of DEMUX1_to_4 is
begin
	o0<=(i and not(sel1)) and not(sel2);
	o1<=(i and not(sel1)) and (sel2);
	o2<=(i and sel1) and not(sel2);
	o3<=(i and sel1) and (sel2);	   
end architecture;