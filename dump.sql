--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customer (
    userid integer NOT NULL,
    paymenttype character varying(255),
    leasestart date,
    leaseend date,
    isapproved boolean,
    unitid integer
);


--
-- Name: maintenancerequest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.maintenancerequest (
    isdealtwith boolean,
    requestid integer NOT NULL,
    "timestamp" date,
    unitid integer,
    userid integer
);


--
-- Name: property; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.property (
    amenities text[],
    propertyid integer NOT NULL,
    address character varying(255),
    rentduedate date,
    name character varying(255),
    communityannouncements text[]
);


--
-- Name: propertymanager; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.propertymanager (
    userid integer NOT NULL,
    isowner boolean,
    propertyid integer
);


--
-- Name: unit; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.unit (
    unitid integer NOT NULL,
    isfurnished boolean,
    rentamount double precision,
    floorplan character varying(255),
    condition character varying(255),
    isrented boolean,
    appliances text[],
    propertyid integer,
    rentpaid boolean,
    rentdue date,
    userid integer
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    userid integer NOT NULL,
    role character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    email character varying(255),
    password character varying(255),
    phonenumber character varying(255)
);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.customer (userid, paymenttype, leasestart, leaseend, isapproved, unitid) FROM stdin;
1248	Cash	2023-10-14	2024-06-20	f	\N
7096	Credit	2023-08-23	2024-03-03	t	\N
220	Credit	2024-06-06	2025-02-14	f	\N
427	Cash	2024-07-26	2025-05-25	f	\N
7332	Credit	2023-07-25	2024-05-27	f	\N
2210	Credit	2024-02-25	2024-10-21	f	\N
1911	Credit	2023-03-22	2023-09-25	t	\N
1033	Credit	2023-02-01	2023-10-12	f	\N
8504	Credit	2024-09-06	2025-04-16	f	\N
4099	Debit	2024-07-16	2025-06-14	t	\N
7863	Credit	2023-05-29	2024-04-02	t	\N
9287	Debit	2023-09-03	2024-06-13	f	\N
6857	Cash	2023-12-16	2024-06-23	t	\N
8715	Credit	2023-11-20	2024-10-21	t	\N
9554	Debit	2024-09-11	2025-04-26	f	\N
6593	Cash	2023-01-31	2024-01-18	f	\N
8596	Cash	2022-10-25	2023-09-29	f	\N
6658	Cash	2023-07-06	2024-01-27	t	\N
2252	Debit	2023-01-24	2023-12-10	f	\N
1261	Cash	2023-08-08	2024-04-08	t	\N
3019	Debit	2024-10-07	2025-09-29	t	\N
5075	Cash	2023-04-14	2023-11-07	f	\N
1327	Debit	2024-08-29	2025-06-19	f	\N
1613	Debit	2023-05-11	2024-03-07	f	\N
7977	Credit	2024-09-01	2025-05-07	t	\N
4887	Cash	2024-10-08	2025-08-08	t	\N
8104	Cash	2024-03-09	2025-01-22	t	\N
9770	Cash	2023-04-28	2024-01-16	t	\N
212	Cash	2024-05-13	2025-02-27	t	\N
4785	Cash	2024-04-12	2024-12-11	f	\N
2992	Credit	2023-07-29	2024-03-31	t	\N
3332	Cash	2024-04-04	2024-11-24	t	\N
4122	Credit	2023-08-04	2024-07-03	t	\N
1678	Cash	2023-05-02	2024-01-16	t	\N
1229	Credit	2024-09-19	2025-06-14	f	\N
4617	Credit	2023-12-01	2024-06-15	f	\N
5021	Credit	2024-07-09	2025-07-09	f	\N
6165	Cash	2024-01-27	2024-07-31	t	\N
2116	Debit	2023-12-23	2024-07-26	f	\N
8898	Cash	2023-03-31	2023-12-18	f	\N
1734	Debit	2024-10-05	2025-05-11	t	\N
4173	Cash	2023-04-26	2024-01-22	f	\N
5865	Debit	2023-08-20	2024-03-01	t	\N
7289	Cash	2023-05-09	2024-04-16	t	\N
5584	Credit	2024-09-22	2025-07-07	t	\N
567	Cash	2023-04-01	2023-10-22	f	\N
2545	Cash	2023-06-17	2024-01-04	f	\N
2237	Debit	2023-01-02	2023-12-19	f	\N
815	Debit	2023-02-15	2023-11-15	f	\N
1839	Debit	2022-11-30	2023-09-27	t	\N
1448	Cash	2022-10-29	2023-07-05	t	\N
9329	Credit	2023-06-25	2024-04-06	t	\N
5483	Cash	2024-02-14	2025-01-15	t	\N
6832	Credit	2024-02-27	2024-11-02	f	\N
5281	Cash	2024-03-20	2025-03-18	f	\N
4023	Debit	2022-11-12	2023-08-02	t	\N
6079	Credit	2023-01-06	2023-07-23	t	\N
\.


--
-- Data for Name: maintenancerequest; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.maintenancerequest (isdealtwith, requestid, "timestamp", unitid, userid) FROM stdin;
t	784	2023-09-18	8910	6832
f	3977	2022-07-31	433	3019
t	2871	2021-06-18	5271	427
t	467	2023-02-03	9410	1248
t	9882	2020-04-10	4407	1448
t	2136	2021-12-20	2323	2252
f	3779	2022-07-15	5467	6832
f	1759	2020-06-14	239	5865
t	7238	2023-08-13	8910	6832
f	3603	2022-07-02	4190	1839
f	7108	2022-09-24	7279	4099
t	5721	2022-10-21	6914	4887
t	1132	2020-12-04	2335	4617
f	1489	2022-08-07	7914	2210
t	253	2021-10-19	3370	1839
t	4668	2022-08-05	5680	5075
t	728	2020-11-11	6098	5483
f	6279	2020-03-21	7279	4099
t	2065	2023-05-04	5842	1327
f	3619	2021-06-12	9803	2992
t	2925	2022-06-14	7914	2210
t	7536	2022-02-15	3820	8596
f	3150	2022-04-12	8805	2237
f	7330	2022-11-12	789	1678
f	6319	2020-06-15	5025	2237
f	6764	2022-10-17	1506	8715
f	3213	2020-12-24	7297	3019
t	8209	2020-04-06	8942	1033
f	667	2021-03-25	4244	1033
t	531	2023-03-01	3084	2116
\.


--
-- Data for Name: property; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.property (amenities, propertyid, address, rentduedate, name, communityannouncements) FROM stdin;
{"BBQ area",spa,"secured parking",playground,clubhouse,"tennis courts",gym}	5089	PSC 1752, Box 1792, APO AE 97326	2024-03-19	Williams, Dyer and Hinton	{"Machine over must national successful require much."}
{"tennis courts"}	503	5655 Tran View, Mooreview, RI 46314	2024-09-01	Osborne LLC	{"South everyone paper avoid.","Tonight oil use mother day theory.","Congress trial operation play fine."}
{playground,spa,gym,pool,"tennis courts",clubhouse,"dog park"}	8559	589 Alexandra Trafficway, Gaychester, MP 93395	2024-01-05	Garcia-Perez	{"Upon product fire piece administration choice improve level.","Fish including young."}
{"BBQ area","dog park",clubhouse,gym,"secured parking",pool}	7387	1735 Nichols Neck, Davidside, AS 99341	2024-03-03	Simmons Group	{"Three sometimes career wind beyond."}
{clubhouse}	5473	PSC 2320, Box 8325, APO AE 06036	2024-03-06	Christensen PLC	{"Cut at candidate seat successful along event.","Difference difficult future question letter determine.","Admit discuss talk leg financial successful."}
{gym,pool,"tennis courts",playground,park,"secured parking",spa,"BBQ area",clubhouse}	9211	824 James Shoal Suite 231, North Raymondview, PR 39787	2024-08-26	Taylor-Thomas	{"Very set entire four wish unit.","Use situation new woman threat."}
{"BBQ area",pool,park,clubhouse,gym,"dog park",spa,"tennis courts",playground,"secured parking"}	9533	60534 Reeves Plaza, Lake Jacob, OR 35194	2023-10-02	Gibson-Jones	{"Both out walk."}
{gym,playground,spa,park,clubhouse,"dog park","tennis courts","secured parking",pool,"BBQ area"}	3550	20404 Oconnor Fields Apt. 298, East Timothyview, SD 16827	2024-05-30	Byrd, Salinas and Castaneda	{"Sister professional for collection.","Cut allow nature woman man walk finally.","Once bag suddenly eight tend."}
{spa,clubhouse,gym,pool,playground,"secured parking"}	6853	26817 Chad Station, Lake Lesliefurt, WY 31655	2024-03-18	Rice PLC	{"Officer my hundred take government her.","Lay choose contain indicate reality evidence.","Weight my notice board should born foot."}
{"BBQ area","dog park"}	9871	48636 Phillip Rapid, Tinastad, PR 65925	2024-04-16	Diaz, Wright and Rogers	{"Newspaper federal campaign music production rule.","Single couple thought car.","Fly political same image kind."}
{playground}	474	010 Walker Corner Suite 412, East Helenshire, WV 74605	2024-06-03	Lewis Ltd	{"Challenge various defense simply nation itself whether.","Example very raise wear expert return."}
{spa,"tennis courts",playground}	9536	5148 Mike Valleys, Lauraburgh, AS 44274	2024-01-05	Riddle, Mcdonald and Mcdonald	{"Administration trade support these amount interesting.","That teacher discuss room until.","Wait mention cell."}
{"BBQ area",gym,"tennis courts",playground,pool,clubhouse}	4403	2713 Sanders Landing, Port Matthewmouth, ND 05029	2022-12-24	Bradley-Fisher	{"Cause yeah by maintain natural among natural.","Ask really strong change property attack since pressure.","Listen lay that same buy ahead night message."}
{"BBQ area","dog park",gym,pool,clubhouse,park,"secured parking","tennis courts",playground}	4290	12586 Murray Lake Suite 835, Watkinschester, MA 25069	2023-12-18	Zimmerman-Green	{"Site turn know."}
{"tennis courts","dog park","secured parking",park,spa,pool,gym,"BBQ area",clubhouse}	759	89597 Moore Track, Robinsonmouth, PA 94733	2024-08-01	Shields, Allison and Baird	{"Theory among military network attorney yeah wife.","Them provide country country difficult building able capital.","Commercial type value edge."}
{"secured parking",clubhouse}	7291	021 Gonzalez Views, Ericfort, AR 90320	2023-02-01	Patrick-Hill	{"Place able chance consumer arrive production employee."}
{playground,"tennis courts","BBQ area",spa,pool,clubhouse}	7732	2020 Bruce Overpass, Brandonfurt, OK 37509	2024-10-01	Yates and Sons	{"Six wish feel cost cover garden."}
{spa,"dog park","tennis courts",pool,"BBQ area"}	7625	168 Jared Trace Suite 133, South Austintown, WA 57598	2024-03-27	Wilson-Roach	{"Individual field resource me."}
{park,pool,playground}	1125	264 Moran Haven, Brookston, FM 35204	2022-12-05	Spencer, Williams and Burgess	{"Tend someone young relate game box ask south.","International value data parent."}
{"BBQ area","tennis courts",pool,"dog park","secured parking",spa,playground,clubhouse,park}	460	250 Jamie Land Apt. 840, Hillton, VI 62803	2024-02-16	Hart and Sons	{"Own arrive painting."}
\.


--
-- Data for Name: propertymanager; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.propertymanager (userid, isowner, propertyid) FROM stdin;
3693	t	7732
453	f	9871
9384	t	9211
292	t	7732
9972	t	474
1285	f	8559
8716	t	3550
3416	f	6853
3721	f	9211
429	f	5089
8271	t	759
2415	f	5473
4794	f	9533
5759	f	474
9338	f	5473
15	f	3550
907	t	6853
5954	t	7625
4247	t	4290
1792	f	9871
8803	t	460
1594	f	3550
6451	t	1125
8549	t	4403
2930	f	4290
4808	f	9536
8953	f	9871
8444	f	6853
8064	f	9533
5608	f	460
8775	f	5473
9061	t	4403
5451	t	9536
1861	f	4403
7138	f	9536
3160	f	7625
1823	f	9536
4764	f	7291
3929	f	9533
5066	f	7625
3864	f	5089
9238	t	7387
1957	f	3550
\.


--
-- Data for Name: unit; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.unit (unitid, isfurnished, rentamount, floorplan, condition, isrented, appliances, propertyid, rentpaid, rentdue, userid) FROM stdin;
9410	f	1701	sister	Used	t	{receive}	4290	f	2023-10-11	1248
5842	f	778	good	New	f	{behavior,line,coach}	9871	t	2023-10-11	1327
3012	f	2884	investment	Good	f	{energy}	9536	f	2023-10-11	6658
6109	f	1841	example	Old	f	{by,modern,difference}	7387	f	2023-10-11	9770
4787	t	1657	thus	Good	f	{democratic,land,area}	7387	f	2023-10-11	1839
8805	f	965	say	Good	t	{section,team,daughter,fund}	5473	f	2023-10-11	2237
2335	f	2478	bar	New	f	{test,her}	7625	t	2023-10-11	4617
8910	t	1914	law	Good	t	{between,according}	7732	t	2023-10-11	6832
3105	t	675	why	Good	t	{because,from,marriage,suffer}	4290	f	2023-10-11	7096
4874	f	1658	perhaps	Old	t	{final}	9533	f	2023-10-11	6165
789	f	1277	so	New	t	{three,who,off,difference}	7387	f	2023-10-11	1678
3215	f	883	decade	Good	t	{local}	759	t	2023-10-11	4887
6914	t	2615	understand	New	t	{control,agreement,free,short}	9536	f	2023-10-11	4887
8110	t	2419	draw	New	t	{Democrat,activity}	9871	f	2023-10-11	9287
3820	f	2686	race	Used	f	{side}	759	f	2023-10-11	8596
4190	f	1478	recent	Good	t	{deep,develop}	503	t	2023-10-11	1839
1506	f	2457	foreign	Good	t	{against,one,offer}	4290	t	2023-10-11	8715
7567	f	803	hit	Good	t	{establish,fly}	1125	f	2023-10-11	4887
4407	t	2846	reality	Used	f	{PM,other,control,might}	9536	t	2023-10-11	1448
7258	f	2179	anyone	Good	f	{collection,heart,special,discussion}	8559	f	2023-10-11	9329
4244	t	2573	skin	Used	t	{total,cover,hot,tough}	9211	f	2023-10-11	1033
5685	f	1552	foot	Old	f	{then}	759	t	2023-10-11	6857
3084	t	1224	material	Old	f	{project,thing,cover,card}	7387	t	2023-10-11	2116
3035	f	1793	before	Used	t	{model,majority,worry}	474	t	2023-10-11	3019
8846	t	2195	night	Good	t	{election,true}	9871	f	2023-10-11	8104
9603	t	1869	message	Good	t	{follow,cause,citizen}	460	t	2023-10-11	9554
433	t	2177	decide	Good	t	{fill,fact}	4290	f	2023-10-11	3019
2323	t	2389	approach	Old	f	{garden,game}	7291	t	2023-10-11	2252
239	f	1687	executive	Old	t	{free,around}	4290	t	2023-10-11	5865
3370	t	1386	so	Used	f	{standard,than}	7625	t	2023-10-11	1839
7569	t	2774	morning	Used	f	{those}	474	t	2023-10-11	212
5025	t	2349	determine	Used	f	{range}	7732	t	2023-10-11	2237
9803	f	2207	in	Good	t	{take}	474	t	2023-10-11	2992
6440	t	1051	join	New	f	{per,red,drive,tree}	4290	t	2023-10-11	9554
7279	t	849	clearly	New	t	{reduce,go,building,respond}	9871	f	2023-10-11	4099
6243	f	2961	water	Old	t	{ahead,kind,experience}	4403	f	2023-10-11	8596
7914	t	938	image	Good	t	{everyone}	9211	f	2023-10-11	2210
5467	f	1055	age	Good	t	{use,drug}	7732	t	2023-10-11	6832
5680	t	1074	unit	Old	f	{long,billion,suffer}	5089	f	2023-10-11	5075
3255	f	1948	product	Used	f	{half,mission,chair}	3550	f	2023-10-11	427
3262	t	1202	school	Old	f	{to,nearly,western}	7387	t	2023-10-11	8504
6990	t	1333	where	Used	t	{budget,meet}	6853	t	2023-10-11	4617
5271	t	2299	among	New	f	{media,rise}	5089	t	2023-10-11	427
6140	t	2989	writer	Old	f	{very,read,might}	6853	f	2023-10-11	1229
6783	t	577	former	Used	f	{each,team,until}	5473	f	2023-10-11	1327
1603	f	1116	current	Used	f	{career,ahead}	7387	f	2023-10-11	427
6098	f	2040	respond	Used	f	{recognize}	7387	f	2023-10-11	5483
7297	t	1136	development	Old	f	{easy,follow,company}	7732	t	2023-10-11	3019
1057	t	568	college	New	f	{sell,bar}	9871	f	2023-10-11	1327
8942	t	1630	artist	New	t	{and,owner,data,but}	9211	f	2023-10-11	1033
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.users (userid, role, firstname, lastname, email, password, phonenumber) FROM stdin;
3693	PropertyManager	Amy	Meyer	anna52@example.net	$K9)YDwRC@	2733083795
453	PropertyManager	Jacob	Nixon	josephanderson@example.org	(v4YWXl)h+	(458)303-8880x88320
9384	PropertyManager	Tammy	Johnson	cody45@example.net	+VANdiQ1I2	001-679-321-1801x368
292	PropertyManager	Tara	Hill	dperez@example.net	Y)*%pKQo@9	+1-930-471-3337x6489
1248	Customer	Timothy	Williams	ebolton@example.org	tBs8XY+hu#	(238)636-3708x822
7096	Customer	William	Roberts	lauriegill@example.net	k!IzqSTeX5	751-575-6738x7269
9972	PropertyManager	Tyler	Villa	martinezjames@example.net	&K6p$eQwxk	(935)999-8351x269
220	Customer	Tina	Moore	theresahumphrey@example.com	A@^@I5LeA0	(865)369-3134x1050
1285	PropertyManager	Jody	Shepherd	mary01@example.org	%0eGV@ikH0	(262)466-1795x54189
427	Customer	Anna	Ryan	ncooper@example.net	6EL)4#rz+1	(911)316-5135x55085
7332	Customer	James	Olson	halericky@example.net	p9zFspjs%m	925.523.1922
2210	Customer	Kayla	Ross	johnsonsarah@example.net	p6)&_3Xz_6	743-910-3703
8716	PropertyManager	Justin	Flowers	rebecca76@example.com	%V9EfeVuv*	453.562.7643
1911	Customer	Shawn	Ayers	jacksondonna@example.com	(A3LQ8Jjfe	001-863-377-3955x940
1033	Customer	Justin	Smith	qgarcia@example.com	n35RD&kZ&j	7489911079
8504	Customer	Phillip	Nguyen	aguilarscott@example.org	&kRmW$Eyw0	+1-676-730-6336x4175
3416	PropertyManager	Steven	Mendez	vvargas@example.com	@95dGh1L^M	(660)555-4624x141
3721	PropertyManager	James	Martin	frederick27@example.org	_Q19XQfwM8	(332)483-0860
429	PropertyManager	Lisa	Collins	kchung@example.com	+_yDX1VsTt	559-355-6124
4099	Customer	William	Hunt	bushkatherine@example.org	&XZhIc6E5P	(212)646-3577
7863	Customer	Christina	Arias	jennifer49@example.com	jiq0IfT44@	592.274.5173x077
9287	Customer	Lori	Mcpherson	mayeremily@example.org	7Z&47cEwPf	(479)672-1528x333
8271	PropertyManager	Sharon	Mahoney	john25@example.com	x5o*kX)*^y	001-409-431-3991x1247
6857	Customer	Vincent	Curtis	fowlergregory@example.com	q#J9mIQl+5	001-785-762-6748x555
2415	PropertyManager	Sarah	Lopez	ereyes@example.com	YW3uZEyUF+	270.537.8615x9958
8715	Customer	Margaret	Moore	francoalyssa@example.org	Dk(c0Ghh)B	605-298-5256x970
9554	Customer	Angela	Hodges	andersontina@example.net	0cba+Jku@1	471.232.7435
6593	Customer	Christina	Richard	diane97@example.net	#Zv6Mk_$@1	001-661-449-7181x97567
4794	PropertyManager	Robert	Jones	vtownsend@example.net	j#1O@#+d$2	(657)572-5705x702
8596	Customer	Francisco	Williams	mcook@example.net	+86(FOSe&5	5639243322
6658	Customer	Kimberly	Clark	smithjoshua@example.net	!_19Yz8m$*	(807)740-0695
2252	Customer	Dawn	Martin	underwoodbradley@example.com	$j6Cx@g$4!	(370)666-7915x937
1261	Customer	Brendan	Sanders	fieldsrenee@example.org	I^Dd4v@ydV	772-329-8363x90515
5759	PropertyManager	Sydney	Rose	martinmary@example.org	O9&37)TaWK	9415952881
3019	Customer	Lisa	Harrison	waltersloretta@example.com	6J(S+rYX)l	862-389-3473x54035
9338	PropertyManager	George	Knight	cooperrobert@example.com	#Tn93Wp5i*	252-678-5554x554
15	PropertyManager	Abigail	Barnes	uandrews@example.com	g3EZ@WKw_x	+1-226-999-2342x20559
5075	Customer	Elizabeth	Meyer	elizabeth79@example.com	sC1j#BwFz#	001-924-339-3477x517
907	PropertyManager	Charles	Keith	chamberstammy@example.net	^sU3K6npw0	(472)302-4324
1327	Customer	Ryan	Salazar	allenamber@example.com	q#5Dq7!ecG	791-923-0128
1613	Customer	Travis	Park	johnsonandrew@example.com	&_K2Nu7k#(	(708)579-5976x02794
7977	Customer	Daniel	Hanna	brenda18@example.net	^sRZgmIQ2D	7778709251
5954	PropertyManager	Michael	Walker	heathermolina@example.net	$x4eY9Af@!	502-905-8298x549
4247	PropertyManager	Sylvia	Bailey	janetwebb@example.com	tW(D*7JdBu	947-245-8090x73780
1792	PropertyManager	Erin	Lopez	garciaangela@example.org	Vqx4eQpU^+	001-835-318-5706x08809
4887	Customer	Michael	Leon	gloriabruce@example.com	674YmG$E@f	(848)595-2091x621
8803	PropertyManager	Stephen	Moore	maria75@example.com	i5M!_)Xr)j	(271)575-7592
8104	Customer	Shannon	Lopez	kevinduffy@example.com	a)0&3Ez!Af	936.870.8495x06787
1594	PropertyManager	Alexandra	Davis	richardsonjuan@example.com	)16Yh0nnFm	526-832-8511
9770	Customer	Anthony	Long	katherine97@example.net	a_4C&sf6u+	305-915-0226
6451	PropertyManager	James	Barajas	vramos@example.com	QbV*7Mf4iO	523.475.8650x53017
8549	PropertyManager	Shannon	Howard	emily33@example.org	$6DHuNdkP7	356-834-7233x1002
2930	PropertyManager	Cynthia	Robertson	michelle41@example.net	$#3VGLo__5	670.635.3164
212	Customer	Dylan	Burke	hallemily@example.net	$pYbJY9f67	242.224.8184x56891
4785	Customer	Heather	Ibarra	dford@example.net	)1w&Bfpxig	291-741-0364
2992	Customer	Katrina	Vasquez	lee07@example.com	l*u3GenvYg	442-731-4333x31659
4808	PropertyManager	Hunter	Lee	chamberstonya@example.com	c6LKjCgz&X	716.874.9966
3332	Customer	Crystal	Huynh	codywright@example.com	#0smW$Tqqh	(469)843-5855x4222
8953	PropertyManager	Theresa	Moore	rgreene@example.org	#s!6QpFxL#	+1-635-883-2253x717
4122	Customer	Jose	Green	ycalhoun@example.org	+7_F%y*Krt	+1-246-621-8751
1678	Customer	Alexis	Medina	jerome27@example.org	3zqc0Qj2!F	895-394-0279x45071
1229	Customer	Thomas	Brown	owilliams@example.net	%lZSP%zOu1	(576)885-1751x6646
4617	Customer	Elizabeth	Gonzales	dillon38@example.com	*30z0UgW47	001-305-283-6343x4189
8444	PropertyManager	Scott	Ellis	lisa35@example.com	%5Ker39zR4	359-528-2450x296
5021	Customer	James	Heath	cindyjohnston@example.com	N!39J_x5HP	356.749.4258
8064	PropertyManager	Diana	Brewer	wgonzalez@example.org	%H0(OPgIJN	3765767412
5608	PropertyManager	Christina	Williams	taylorjeffrey@example.org	1FRqiUuk@a	957-563-8930x6323
6165	Customer	Pamela	Mcintosh	joseph22@example.net	8m7aLUwql$	2373227845
2116	Customer	Gary	Wagner	annewhite@example.com	xl#4oWuug1	(795)794-9659
8775	PropertyManager	Sheryl	Stone	gilbertashley@example.org	Lf6N$JQoE!	+1-650-699-6353x27620
8898	Customer	Mary	Henderson	mark82@example.org	qr76PU+Tu)	620.838.9699
9061	PropertyManager	Christine	Hodges	kdawson@example.org	b$6x53ExP*	(376)652-8235
1734	Customer	Jennifer	Mitchell	murrayandrew@example.com	!2!WXzLT^E	001-864-989-0335x28944
5451	PropertyManager	Richard	Valentine	mercadoamanda@example.net	(j3gS1Se!*	001-347-312-2930x1677
4173	Customer	Jacqueline	Aguilar	emma94@example.com	T8xW%%e$(w	222-743-2981x480
1861	PropertyManager	Brooke	Cox	rpowell@example.com	#dMJa4hz+8	+1-245-271-1941x61283
5865	Customer	Sean	Ashley	jamesnorris@example.net	%7%IWoIpVF	001-792-799-8430x464
7289	Customer	Miranda	Fox	angelatyler@example.com	s@ds81QiU^	001-759-972-6470
7138	PropertyManager	Michael	Johnson	angela68@example.net	(RW&w@1da6	815-705-1914
5584	Customer	Tara	Ortiz	vtran@example.org	%(K1ZrHddH	(932)753-4146
567	Customer	Cody	Prince	michellestevenson@example.com	%4VHdVat3F	287-287-2203x488
2545	Customer	Joseph	Bender	mgarcia@example.org	^I+HrFrN_1	(368)456-4929x338
2237	Customer	Sergio	Robertson	xgeorge@example.org	O*L1T@VvdI	(340)667-8035x248
815	Customer	Adriana	Holland	davisallison@example.org	T^8RZdRj)S	980-968-2393x7940
3160	PropertyManager	Kevin	Villanueva	sjones@example.com	_W4vNmKE#5	277-715-4206x807
1823	PropertyManager	Samuel	Williams	smcclain@example.net	zd2EDRoB)0	766-515-6063
1839	Customer	Katherine	Bell	whitneysarah@example.com	P6v8pW5d*P	(806)385-4337x496
4764	PropertyManager	Kathy	Garcia	caleb84@example.org	cu7lLomQ^j	(212)728-5689
1448	Customer	Dana	Tucker	teresagallegos@example.com	n0#%0Z#hO$	8565044339
3929	PropertyManager	Jeffrey	Moore	blackhannah@example.org	^2Vf*avEq!	(908)665-1847
9329	Customer	Erin	Cuevas	shannonbaker@example.com	+Mx7pWadpi	731-729-3054x45741
5483	Customer	Michael	Hayes	rosekaitlin@example.org	lK0Vo8xg+A	+1-971-283-5608x72468
5066	PropertyManager	Adam	Riggs	plopez@example.net	&4t8XUQKtk	(565)486-4318x699
3864	PropertyManager	Terri	Delgado	jennifer25@example.net	p!GC5BTbpN	828.666.4073x0351
6832	Customer	Dustin	Black	jwilliams@example.com	aMxMg4f3^9	+1-750-922-9907x63807
9238	PropertyManager	Michael	Jones	theresa89@example.org	$!MU5LKvw#	5412215565
5281	Customer	Jacqueline	Walker	nelsonalice@example.org	&%Uj9C_gpj	+1-774-258-3343x17695
1957	PropertyManager	Dean	Bray	ptownsend@example.org	90U7GnEq%A	202.421.1761
4023	Customer	Kevin	Martinez	ilopez@example.com	*&vQUg^uw1	515.896.7482x445
6079	Customer	Charles	Perez	william31@example.com	uD7wGaLe^)	001-258-615-2747
\.


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (userid);


--
-- Name: maintenancerequest maintenancerequest_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.maintenancerequest
    ADD CONSTRAINT maintenancerequest_pkey PRIMARY KEY (requestid);


--
-- Name: property property_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.property
    ADD CONSTRAINT property_pkey PRIMARY KEY (propertyid);


--
-- Name: propertymanager propertymanager_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.propertymanager
    ADD CONSTRAINT propertymanager_pkey PRIMARY KEY (userid);


--
-- Name: unit unit_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.unit
    ADD CONSTRAINT unit_pkey PRIMARY KEY (unitid);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);


--
-- Name: customer customer_unitid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_unitid_fkey FOREIGN KEY (unitid) REFERENCES public.unit(unitid) ON DELETE SET NULL;


--
-- Name: unit fk_unit_customer; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.unit
    ADD CONSTRAINT fk_unit_customer FOREIGN KEY (userid) REFERENCES public.customer(userid) ON DELETE SET NULL;


--
-- Name: maintenancerequest maintenancerequest_unitid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.maintenancerequest
    ADD CONSTRAINT maintenancerequest_unitid_fkey FOREIGN KEY (unitid) REFERENCES public.unit(unitid) ON DELETE CASCADE;


--
-- Name: maintenancerequest maintenancerequest_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.maintenancerequest
    ADD CONSTRAINT maintenancerequest_userid_fkey FOREIGN KEY (userid) REFERENCES public.customer(userid) ON DELETE SET NULL;


--
-- Name: propertymanager propertymanager_propertyid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.propertymanager
    ADD CONSTRAINT propertymanager_propertyid_fkey FOREIGN KEY (propertyid) REFERENCES public.property(propertyid) ON DELETE SET NULL;


--
-- Name: unit unit_propertyid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.unit
    ADD CONSTRAINT unit_propertyid_fkey FOREIGN KEY (propertyid) REFERENCES public.property(propertyid) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

