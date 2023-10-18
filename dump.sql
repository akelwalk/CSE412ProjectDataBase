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
    unitid integer,
    propertyid integer
);


--
-- Name: maintenancerequest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.maintenancerequest (
    isdealtwith boolean,
    requestid integer NOT NULL,
    "timestamp" date,
    propertyid integer,
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
    rentdue timestamp without time zone,
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

COPY public.customer (userid, paymenttype, leasestart, leaseend, isapproved, unitid, propertyid) FROM stdin;
4313	Cash	\N	\N	f	\N	\N
9965	Credit	\N	\N	f	\N	\N
5627	Cash	\N	\N	f	\N	\N
5401	Credit	\N	\N	f	\N	\N
110	Credit	\N	\N	f	\N	\N
1147	Debit	\N	\N	f	\N	\N
9314	Credit	\N	\N	f	\N	\N
6253	Cash	\N	\N	f	\N	\N
3195	Debit	\N	\N	f	\N	\N
3854	Debit	\N	\N	f	\N	\N
9674	Cash	\N	\N	f	\N	\N
8954	Debit	\N	\N	f	\N	\N
1710	Cash	\N	\N	f	\N	\N
1192	Debit	\N	\N	f	\N	\N
6573	Debit	\N	\N	f	\N	\N
9782	Cash	\N	\N	f	\N	\N
1863	Credit	\N	\N	f	\N	\N
1279	Cash	\N	\N	f	\N	\N
251	Debit	\N	\N	f	\N	\N
8226	Credit	\N	\N	f	\N	\N
6524	Debit	\N	\N	f	\N	\N
9180	Credit	\N	\N	f	\N	\N
729	Cash	\N	\N	f	\N	\N
8065	Credit	\N	\N	f	\N	\N
4421	Cash	\N	\N	f	\N	\N
5451	Cash	\N	\N	f	\N	\N
8466	Credit	\N	\N	f	\N	\N
2332	Cash	\N	\N	f	\N	\N
4628	Debit	\N	\N	f	\N	\N
788	Credit	\N	\N	f	\N	\N
6511	Credit	\N	\N	f	\N	\N
4820	Debit	\N	\N	f	\N	\N
4367	Credit	\N	\N	f	\N	\N
6795	Debit	\N	\N	f	\N	\N
269	Debit	\N	\N	f	\N	\N
5659	Cash	\N	\N	f	\N	\N
1367	Cash	\N	\N	f	\N	\N
984	Credit	2023-06-11	2023-12-27	t	1798	9683
3017	Credit	2024-07-17	2025-02-03	t	1309	9683
8910	Debit	2023-04-20	2024-03-20	t	9166	6054
9475	Cash	2022-11-30	2023-06-15	t	4144	6899
4699	Debit	2023-03-05	2024-01-07	t	1554	1448
8475	Debit	2022-11-23	2023-07-06	t	7804	4659
2039	Credit	2023-12-23	2024-10-28	t	6540	6694
6038	Cash	2024-02-12	2024-10-10	t	8827	8787
6362	Cash	2023-07-14	2024-03-10	t	7149	6694
2621	Debit	2024-01-27	2024-08-14	t	3293	9683
1766	Debit	2022-11-27	2023-09-28	t	3791	9683
794	Cash	2023-02-22	2023-10-30	t	1523	1604
5037	Credit	2024-01-02	2024-12-15	t	540	981
9466	Cash	2024-09-29	2025-06-18	t	2790	1448
4418	Credit	2022-12-28	2023-09-16	t	9285	3715
7266	Debit	2023-02-03	2023-08-09	t	9769	981
7331	Debit	2022-11-05	2023-09-30	t	8324	3715
9900	Cash	2022-11-10	2023-08-17	t	4583	3386
874	Credit	2024-10-09	2025-04-22	t	77	4259
1489	Cash	2023-10-07	2024-05-08	t	2815	6694
2647	Cash	2023-09-05	2024-03-31	t	195	3386
9831	Credit	2023-12-21	2024-07-07	t	6078	6899
1124	Cash	2022-11-04	2023-05-14	t	9828	7642
2987	Cash	2024-06-12	2025-01-06	t	3704	4659
6642	Debit	2024-03-10	2024-11-11	t	463	8818
3256	Credit	2024-05-19	2025-04-28	t	832	5240
3067	Cash	2022-12-20	2023-09-15	t	2074	3386
1643	Debit	2024-07-22	2025-01-29	t	8588	8818
7828	Credit	2023-05-30	2024-03-04	t	8199	7706
441	Cash	2023-12-09	2024-07-29	t	9651	7642
9291	Credit	2023-09-20	2024-08-12	t	5184	7706
7516	Debit	2024-05-15	2024-12-25	t	9511	3386
2247	Credit	2024-07-01	2025-04-26	t	8068	7642
467	Credit	2023-03-14	2023-11-01	t	7591	9815
5310	Credit	2024-02-27	2024-12-18	t	1084	4534
9173	Debit	2023-07-27	2024-03-08	t	6108	9683
3204	Debit	2023-04-19	2024-01-12	t	3575	9683
\.


--
-- Data for Name: maintenancerequest; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.maintenancerequest (isdealtwith, requestid, "timestamp", propertyid, unitid, userid) FROM stdin;
t	2913	2023-10-11	6694	2815	1489
t	3818	2023-10-12	981	540	5037
t	2960	2023-01-08	3386	9511	7516
t	6313	2020-11-21	1448	1554	4699
f	3447	2022-07-16	5240	832	3256
t	8364	2021-02-08	7642	8068	2247
t	3546	2023-06-04	9815	7591	467
t	9661	2022-04-17	6694	2815	1489
f	9819	2022-03-07	6054	9166	8910
f	7965	2023-09-09	6899	4144	9475
t	7938	2023-04-14	7706	5184	9291
t	7978	2020-08-24	1604	1523	794
f	5592	2022-06-07	3715	8324	7331
f	9303	2020-03-30	3386	4583	9900
f	1370	2023-08-05	1448	1554	4699
f	7355	2020-07-13	4534	1084	5310
t	1110	2022-01-11	6694	7149	6362
f	8665	2022-08-12	6899	4144	9475
t	308	2020-08-15	9683	3575	3204
f	5913	2021-04-29	7706	5184	9291
f	6839	2022-01-13	1604	1523	794
f	7235	2020-01-31	6694	6540	2039
f	5987	2023-04-28	1604	1523	794
t	5098	2020-12-12	4534	1084	5310
f	7576	2023-04-29	1604	1523	794
f	6988	2023-06-28	7642	9651	441
t	6800	2023-05-05	6694	2815	1489
f	9189	2022-12-08	6694	7149	6362
f	199	2020-07-06	3386	2074	3067
t	4471	2021-03-27	5240	832	3256
\.


--
-- Data for Name: property; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.property (amenities, propertyid, address, name, communityannouncements) FROM stdin;
{park,"dog park"}	4659	281 Harrison Courts, North Kellyview, MO 15193	Peterson, Harper and Burgess	{"Us thing material news phone.","Prevent affect physical matter scientist realize.","Least interest I TV."}
{"secured parking",pool,"dog park",spa,clubhouse,gym,"tennis courts","BBQ area"}	6694	27460 Watson Camp, Wilsonstad, CT 85559	Lambert Ltd	{"Nice more agreement time money tough medical.","Relationship example you page benefit."}
{playground}	1448	17898 Floyd Pine Suite 548, Shahport, VI 55738	Gonzalez Ltd	{"Trouble reveal happen woman head cup yard.","Far suggest real seven officer gun decade."}
{"dog park","secured parking",park,playground,clubhouse,pool}	1604	7388 Sweeney Pass, Lake Toddfurt, AR 38944	Wheeler, Valenzuela and Lee	{"Where concern choose want indicate thank able.","Common he activity every station people name.","Director himself spend fire class quality something."}
{clubhouse,pool,"secured parking","dog park","tennis courts",playground,park,"BBQ area",spa}	3386	28455 Stacy Summit Apt. 648, New Rebeccaview, MH 13319	Donovan, Parsons and Perry	{"Father cause if weight team."}
{"BBQ area",pool,"secured parking",gym,"dog park",spa,"tennis courts",playground,clubhouse}	3715	9355 Jesse Ford Apt. 082, East John, SD 49788	Moreno LLC	{"Especially scientist positive consumer wife worry tell.","Those behavior have soon."}
{"dog park",gym,pool,spa,"secured parking","tennis courts"}	7706	18282 Paul Lodge Suite 359, Leebury, WA 72217	Miller-Porter	{"Improve two be recognize.","Skin close movie course.","Black herself member figure seem."}
{"BBQ area"}	9732	226 Sarah Creek Suite 278, Smithchester, VT 39021	Willis-Anderson	{"No matter it outside positive strong enjoy.","Speech work glass season."}
{playground,"secured parking","BBQ area",gym,"dog park"}	8787	1244 Trevor Lodge, West Kyleborough, MN 04764	Robbins, Leblanc and Mendoza	{"Though friend reach budget.","Edge take note in security summer government certain.","Detail word try through draw series news."}
{clubhouse,"BBQ area","secured parking","dog park","tennis courts",playground,pool,spa,gym,park}	4534	596 Linda Via Suite 956, Porterfurt, GA 15204	Powers Ltd	{"Government police generation unit language authority have.","Which part though.","Reach natural fly trip argue science."}
{park,"secured parking",spa,playground,"dog park"}	9683	38769 Hall Field, Kathrynville, NC 86501	Russo PLC	{"Painting usually very game subject rate heart some."}
{pool,playground,"secured parking",clubhouse,"tennis courts"}	9815	705 Cooper Throughway, Omarmouth, MH 04280	Morris-Foster	{"Head popular guess tree likely then close.","Respond heart yourself enter environmental."}
{park,"tennis courts",playground}	6054	951 James Burg, Andersontown, LA 99369	Williams, Hansen and Callahan	{"Operation itself leave parent network.","Purpose range nor fact tonight."}
{playground,"dog park","BBQ area",clubhouse,"secured parking",gym,park,spa,"tennis courts",pool}	981	42731 Michael Spur Suite 370, South Michael, UT 12474	Blankenship Inc	{"Value prove such two rich."}
{playground,gym,"dog park",clubhouse,"secured parking"}	6899	191 Jennifer Causeway Suite 302, Cherryborough, SD 09994	Allen, Dodson and Kramer	{"Respond particularly save long he field question.","By check agree few provide."}
{"dog park",spa,"tennis courts","BBQ area",gym,pool,park,clubhouse}	8818	468 Craig Mountain Suite 192, Brianview, NY 20401	Zuniga Ltd	{"Myself south understand candidate.","Plan energy strategy church.","Per thing television run health lose physical."}
{gym,park,"BBQ area","tennis courts","secured parking"}	4259	3141 Joshua Rue Apt. 221, Crystalshire, SC 12785	Ferguson and Sons	{"Class research face floor.","Want certainly successful good politics scene.","City audience ask fund him."}
{playground,"secured parking",pool,clubhouse,gym,park,"BBQ area"}	5240	96237 Melissa Pines, North Reginachester, GU 06294	Harvey Inc	{"World activity every."}
{park,"tennis courts",pool,clubhouse,gym,"secured parking",playground,"dog park"}	5403	1476 Mark Oval, Lake Colleenchester, CO 67052	Cervantes-Berry	{"Customer prove road control do put develop."}
{"BBQ area",park,gym,"dog park",pool,clubhouse,spa,playground,"tennis courts","secured parking"}	7642	4581 Christopher Road Apt. 804, North Denise, PW 70146	Nguyen LLC	{"Mouth foot my model her."}
\.


--
-- Data for Name: propertymanager; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.propertymanager (userid, isowner, propertyid) FROM stdin;
423	t	9732
3267	t	9732
8360	f	7706
4362	f	1448
570	t	7706
5455	t	1448
3728	f	6694
6945	f	6694
1623	t	4259
8320	t	5240
6049	t	7642
7788	f	3386
6931	t	4259
2469	f	1448
3637	t	3715
9273	f	7706
2906	t	8818
1562	f	8818
4180	t	5240
1338	f	5240
5678	f	7642
4745	f	3715
1799	t	6899
461	t	4659
6012	f	8818
8158	t	4534
\.


--
-- Data for Name: unit; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.unit (unitid, isfurnished, rentamount, floorplan, condition, isrented, appliances, propertyid, rentpaid, rentdue, userid) FROM stdin;
1798	t	1624	Loft	Old	t	{"Coffee Maker",Dishwasher}	9683	t	2023-10-17 23:59:59	984
1309	t	2930	Penthouse	Good	t	{Dishwasher}	9683	t	2023-10-17 23:59:59	3017
9166	f	2510	Loft	Used	t	{"Washing Machine",Refrigerator,Dryer,Blender}	6054	f	2023-10-17 23:59:59	8910
4144	f	2594	Penthouse	New	t	{"Washing Machine",Dryer,"Coffee Maker"}	6899	f	2023-10-17 23:59:59	9475
1554	f	2064	2 Bedroom	Good	t	{Toaster,Dryer,Oven}	1448	f	2023-10-17 23:59:59	4699
7804	t	1867	Loft	Used	t	{Refrigerator,"Washing Machine"}	4659	t	2023-10-17 23:59:59	8475
6540	f	647	3 Bedroom	New	t	{"Coffee Maker"}	6694	f	2023-10-17 23:59:59	2039
8827	t	2719	Studio	Good	t	{"Coffee Maker",Dishwasher,Refrigerator,Blender}	8787	t	2023-10-17 23:59:59	6038
7149	t	1249	Penthouse	New	t	{"Coffee Maker"}	6694	f	2023-10-17 23:59:59	6362
3293	f	555	3 Bedroom	New	t	{Blender,Oven,"Washing Machine","Coffee Maker"}	9683	t	2023-10-17 23:59:59	2621
3791	t	2121	2 Bedroom	New	t	{Oven,Dishwasher,Blender,Refrigerator}	9683	t	2023-10-17 23:59:59	1766
1523	f	718	Studio	New	t	{"Coffee Maker",Microwave}	1604	t	2023-10-17 23:59:59	794
540	f	1322	Studio	Good	t	{"Coffee Maker"}	981	f	2023-10-17 23:59:59	5037
2790	t	1117	3 Bedroom	Good	t	{Toaster}	1448	t	2023-10-17 23:59:59	9466
9285	t	2908	3 Bedroom	Good	t	{"Washing Machine",Oven}	3715	f	2023-10-17 23:59:59	4418
9769	t	1235	2 Bedroom	Used	t	{Blender,Oven,"Washing Machine"}	981	t	2023-10-17 23:59:59	7266
8324	t	2228	1 Bedroom	Good	t	{"Washing Machine","Coffee Maker",Dryer}	3715	f	2023-10-17 23:59:59	7331
4583	t	1436	Studio	Old	t	{Blender,"Washing Machine","Coffee Maker",Oven}	3386	f	2023-10-17 23:59:59	9900
77	f	2081	2 Bedroom	Good	t	{"Coffee Maker"}	4259	t	2023-10-17 23:59:59	874
2815	t	2979	2 Bedroom	Used	t	{Refrigerator}	6694	f	2023-10-17 23:59:59	1489
195	f	1606	Loft	Good	t	{"Coffee Maker",Dishwasher,Microwave}	3386	t	2023-10-17 23:59:59	2647
6078	f	842	Studio	Good	t	{Toaster}	6899	t	2023-10-17 23:59:59	9831
9828	f	624	Studio	Good	t	{Toaster,"Coffee Maker",Dishwasher,Refrigerator}	7642	t	2023-10-17 23:59:59	1124
3704	f	2570	Loft	Old	t	{Refrigerator,Blender}	4659	t	2023-10-17 23:59:59	2987
463	f	1609	2 Bedroom	Used	t	{Refrigerator,Microwave}	8818	f	2023-10-17 23:59:59	6642
832	f	1836	Loft	Old	t	{Microwave,Toaster,"Coffee Maker",Refrigerator}	5240	f	2023-10-17 23:59:59	3256
2074	f	2327	1 Bedroom	New	t	{Dishwasher}	3386	t	2023-10-17 23:59:59	3067
8588	f	653	Penthouse	Used	t	{Oven,"Coffee Maker",Refrigerator}	8818	f	2023-10-17 23:59:59	1643
8199	t	1125	Penthouse	Used	t	{Microwave,"Washing Machine"}	7706	f	2023-10-17 23:59:59	7828
9651	f	750	Penthouse	New	t	{Toaster}	7642	f	2023-10-17 23:59:59	441
5184	t	1413	Loft	Old	t	{Refrigerator,Microwave,"Washing Machine","Coffee Maker"}	7706	t	2023-10-17 23:59:59	9291
9511	t	1364	3 Bedroom	New	t	{Blender,"Coffee Maker",Dishwasher}	3386	f	2023-10-17 23:59:59	7516
8068	f	1216	1 Bedroom	Old	t	{Refrigerator,Blender}	7642	t	2023-10-17 23:59:59	2247
7591	t	2053	3 Bedroom	Old	t	{"Coffee Maker",Toaster,Refrigerator,Dryer}	9815	f	2023-10-17 23:59:59	467
1084	f	1843	Loft	Old	t	{Dryer,Refrigerator,Toaster}	4534	t	2023-10-17 23:59:59	5310
6108	f	2915	2 Bedroom	New	t	{Microwave,Toaster,"Coffee Maker"}	9683	f	2023-10-17 23:59:59	9173
3575	t	2998	Loft	Used	t	{"Washing Machine",Oven,Dishwasher,Dryer}	9683	t	2023-10-17 23:59:59	3204
9525	t	1688	1 Bedroom	Good	f	{Toaster,Refrigerator,"Coffee Maker","Washing Machine"}	4534	f	2023-10-17 00:00:00	\N
8806	t	1289	1 Bedroom	Used	f	{Oven,Dishwasher,"Coffee Maker"}	7706	f	2023-10-17 00:00:00	\N
2974	f	1704	1 Bedroom	Old	f	{Toaster,Dishwasher,Dryer}	4534	f	2023-10-17 00:00:00	\N
8129	f	2688	Studio	Good	f	{Refrigerator}	3386	t	2023-10-17 00:00:00	\N
7563	f	1758	2 Bedroom	New	f	{Microwave,"Washing Machine"}	5240	f	2023-10-17 00:00:00	\N
4733	t	1737	Penthouse	Good	f	{Oven,Microwave}	7642	t	2023-10-17 00:00:00	\N
6199	t	2487	Studio	Good	f	{"Coffee Maker"}	8818	f	2023-10-17 00:00:00	\N
502	f	1570	2 Bedroom	Old	f	{Oven,Dishwasher,Blender}	6054	t	2023-10-17 00:00:00	\N
8840	t	1437	1 Bedroom	Old	f	{Blender,"Washing Machine",Dishwasher,Refrigerator}	9732	f	2023-10-17 00:00:00	\N
9060	f	2900	2 Bedroom	Old	f	{Oven,Dishwasher}	7706	t	2023-10-17 00:00:00	\N
4739	t	1323	3 Bedroom	Good	f	{Toaster,Microwave,Dishwasher}	7642	t	2023-10-17 00:00:00	\N
156	f	1216	Studio	Old	f	{Microwave,Blender,"Coffee Maker",Oven}	981	t	2023-10-17 00:00:00	\N
9296	t	536	1 Bedroom	New	f	{Oven,Dryer,Toaster}	9815	f	2023-10-17 00:00:00	\N
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.users (userid, role, firstname, lastname, email, password, phonenumber) FROM stdin;
4313	Customer	Susan	Jackson	mjones@example.net	^6NXifTvnW	779.649.3773
423	PropertyManager	Lori	Allison	ewilliams@example.com	3Ee9FErv#&	001-541-676-8878
3267	PropertyManager	Rachel	Rodriguez	anthony30@example.net	^daZ6sw4l5	835.821.1062
6038	Customer	William	Harvey	daviseric@example.net	)GHbnip(J2	399-536-5564x04765
9965	Customer	Joseph	Greer	clyons@example.org	f*&67Mt_aT	001-546-808-1035x20939
5627	Customer	Amy	Gonzales	colleen49@example.net	+6ULJa&w14	901.245.4320
2039	Customer	Teresa	Wright	kennethtaylor@example.net	x5Kr@Y)T#Q	441-746-8072
4699	Customer	Jeffrey	Stanley	kathy19@example.com	K$7MdXUl_M	646-559-1360x466
8360	PropertyManager	Frances	Chan	ginafischer@example.net	)5Qm82aacX	856-843-6170x72852
5401	Customer	Maria	West	christophergarrett@example.net	y0W_34St9q	001-415-699-2799
4362	PropertyManager	Christina	Hunter	ucruz@example.org	&2muoHGcap	537-657-6421
110	Customer	Daniel	Stevens	phillip30@example.com	2(41Hj7gQq	001-324-530-3386x1111
570	PropertyManager	Erika	Miller	hurleyandrew@example.com	%Ll8#XawrN	+1-685-602-0454x4914
1147	Customer	John	Davidson	hollybrooks@example.com	bHH3Px*rL+	(983)290-9338x859
9314	Customer	Morgan	Cox	sheila09@example.com	o@1qs!Bqcd	(957)464-9897
6253	Customer	Joseph	Gardner	scott53@example.com	+g5AWAHl6u	588-375-7021x5221
3195	Customer	Jamie	Carlson	zbernard@example.org	g4)qWzjM%4	391.798.0222
3854	Customer	William	Decker	barrerameghan@example.com	2P#L2SYa8d	(481)439-5948x052
5455	PropertyManager	Lisa	Hartman	geralddunn@example.org	%b0R5Tk%p%	(231)432-7221
2647	Customer	John	Rowe	amanda21@example.net	!x$(K!4Ze7	001-342-219-3347
3728	PropertyManager	Jennifer	Mclaughlin	samanthacummings@example.org	@9$cL^!8z(	792-298-6671
6945	PropertyManager	Stephanie	Lamb	zsalazar@example.org	aN5T_OTy!#	+1-506-720-5779
9674	Customer	Melanie	Baker	gregoryjenkins@example.com	g$#5Z7r0Rv	5553656546
1623	PropertyManager	Diana	Wiley	lynnyu@example.com	m@DbHZnn$7	(452)489-5568x1638
7331	Customer	Ryan	Garcia	lsalazar@example.net	#n&WTaSa*0	001-895-974-0445
8320	PropertyManager	Grace	Pena	rhonda43@example.com	0#IhZap1(P	408-581-0139x437
7266	Customer	Kimberly	Martin	gonzalezthomas@example.com	tPbK1r5e)7	297-202-4657x9318
6049	PropertyManager	Julie	Morrison	petersonchristopher@example.net	a4u4LKkk!t	001-852-934-8425x208
794	Customer	Michael	King	iwilson@example.org	$@47(GE7+a	352.778.6493x7189
467	Customer	Brandon	Foster	aramirez@example.com	Jl27B$MFM^	645.297.1430x08603
9831	Customer	Paul	Anderson	ydean@example.org	Wv81Q_2h%8	537.291.9261x440
8954	Customer	William	Jenkins	mitchellelizabeth@example.org	*F)0KIhzHU	(310)423-0688
5310	Customer	Kathleen	Thomas	xtaylor@example.net	^$qjGKJcd7	9772089618
7788	PropertyManager	Nathaniel	Dalton	boydgregory@example.org	^43JT6brFU	880.826.4180x082
1710	Customer	Nicole	Carter	shaney@example.com	*mKydb_U7s	348.808.7457
9291	Customer	Amy	Evans	cwheeler@example.net	(YD!wLjjN5	(443)996-1601x12338
2987	Customer	Russell	Ellis	marshallmonica@example.org	t(afp5Vm&9	(757)895-0274x66244
3256	Customer	Christina	Archer	kwright@example.com	4)Kdm_Sh$6	218-242-2075
6931	PropertyManager	Eddie	Smith	fsnyder@example.com	$3iHTyF9Ic	907-657-8159x97751
1192	Customer	Austin	Smith	owatson@example.org	!1XwUecqZs	883-477-3417
6573	Customer	Mary	Barnett	katherine38@example.org	)#VP4fLc3w	635-339-8287x06101
9782	Customer	Gregory	Valentine	kimberlysalas@example.org	M(3SV%_k@H	571.753.3668x721
2469	PropertyManager	Leah	Spencer	lpatterson@example.com	@48EJsD3iH	+1-812-315-3651x8377
874	Customer	Angela	Little	lburton@example.org	p7Lx6H1z$3	001-335-288-4624x10869
3637	PropertyManager	Danielle	Curtis	vcox@example.org	M5d6OPuH@7	646-697-1305
1863	Customer	Michael	Gonzales	pinedamadeline@example.com	(aKak3Mhfi	+1-767-803-4476x39629
1279	Customer	Jacob	Castro	andrehart@example.net	mI61Bkpl@*	301.364.7044
1489	Customer	Kathryn	Keller	dianamorgan@example.org	!1VJZetn_w	252.549.6140
251	Customer	Melanie	Martinez	kaguilar@example.org	uqfJCFYn(7	656-296-7884x1832
8226	Customer	Timothy	Smith	trobinson@example.net	00uxA3Xf#W	+1-527-301-8693x783
6524	Customer	Michael	Cabrera	jesus08@example.org	T8dvLmbe(c	+1-427-739-2230
1124	Customer	Karen	Norris	lindseyisaac@example.org	Gkf^9Bp9@C	688.585.0514
9180	Customer	Shane	Shaw	cherylsnyder@example.org	57!)2GurE7	635.213.0566
3067	Customer	Andres	Martinez	bakersarah@example.org	JH9uz16in%	+1-903-534-3139
9900	Customer	Gloria	Gallegos	millermegan@example.org	(FQQ!r5l3m	(227)730-6223x407
729	Customer	Jason	Smith	mgibbs@example.net	JJ9O0CuB+t	(328)864-3091
9173	Customer	Jillian	Lee	kyletaylor@example.com	eJ(t1(Ksi0	452-992-8086
8065	Customer	Matthew	Brock	scott00@example.net	6(AW_1Fhfx	846.680.6448x6469
9273	PropertyManager	Katie	Carter	lisawest@example.org	c_g^3SRlL!	(626)495-7897x5485
4418	Customer	Michelle	Ayala	garymoran@example.com	@2xHGfHZvu	505-739-7459x092
984	Customer	Matthew	Ford	nielsenmichelle@example.org	_JJKSn+m3I	285-424-6724x53585
2906	PropertyManager	Mark	Mitchell	richardgreene@example.com	mI+uWClc%1	+1-550-218-0477x7282
1562	PropertyManager	Diana	Marshall	traciromero@example.net	t@04JJfO#C	+1-952-854-4228x0614
4421	Customer	Scott	Walls	cstanley@example.net	!d7CtNNrdX	001-875-867-5963x43084
9475	Customer	Jesse	Bennett	debrabrown@example.com	np41HzPt8&	478-576-0330
5451	Customer	Jesse	Lopez	nedwards@example.org	!1FeHHHU)o	(595)385-9285x90925
4180	PropertyManager	Brenda	Dean	lyonsanthony@example.org	J!^B8YoJ8Q	+1-572-243-6615x954
1338	PropertyManager	Charles	Campbell	joannlozano@example.com	#*6%OanSe%	(699)965-6596x7717
8466	Customer	Peter	Carr	turnerchristopher@example.net	TzQBxV5n$3	614.746.1828x606
5037	Customer	Michael	Hill	sandra33@example.org	(k2Q*nQ0ZO	+1-530-480-9239x21515
2332	Customer	Alexis	Jones	pennysmith@example.com	D!Ee@+kz!0	862.474.5450
1766	Customer	Amber	Reilly	kurtjohnson@example.org	$@V*vwv029	001-880-690-4783x678
4628	Customer	Karina	Joseph	mistymcmillan@example.net	@3tPXm$yya	+1-889-640-3152
3017	Customer	David	York	ryanelizabeth@example.org	_*2gXsLtRq	(968)590-2496x69281
5678	PropertyManager	Mark	Jennings	wbarry@example.com	#rSwwe4CW0	437-926-9567x95265
4745	PropertyManager	Whitney	Carter	emma15@example.net	%91H9JaDXC	001-547-321-5341x099
788	Customer	Steven	Anderson	tammy40@example.net	$aOKgx54Z5	702-725-3338
1799	PropertyManager	Ashley	Stephens	kendrahall@example.org	Q@JEj#bd#8	001-331-714-4917x67307
7516	Customer	Brett	Knight	marie47@example.com	Ps9$BhC@6&	+1-888-380-1786
461	PropertyManager	Laura	Greene	byrdangela@example.org	^KJJYObg0C	(670)792-7027
8475	Customer	Cynthia	Bell	johncole@example.org	++BoVsPNc6	001-545-328-6438x789
6012	PropertyManager	Rick	Burnett	zbenson@example.org	(Et)lE9x49	001-760-582-6636x51743
9466	Customer	Jaime	Villanueva	michael46@example.com	uixB$pBi)8	738-997-6051
6511	Customer	Casey	Smith	xkramer@example.net	F*Nw^6YR8y	938-233-7970x9529
2621	Customer	Carla	Jones	heather16@example.net	Zg5RPZcGd!	+1-832-518-2593
4820	Customer	Emily	Perry	mcmillannathan@example.org	*!E1pVsu5z	448.875.7362x66991
4367	Customer	William	Stephenson	christopher29@example.com	+1WRPF6mrh	956.916.9235x7438
6795	Customer	Lisa	Caldwell	icruz@example.net	Fx_7EdnlLT	885-751-5628
1643	Customer	Vanessa	Phillips	danielmccarty@example.org	^0XG0zi%y8	001-966-702-0433x380
269	Customer	Suzanne	Zimmerman	gramirez@example.net	74ce8xMi@O	(582)396-8382
5659	Customer	Adam	Malone	steven65@example.com	c9FIuJ6d_M	360-801-7278x309
1367	Customer	Kathleen	Holt	kristin06@example.net	1myFKgTk+J	001-320-565-4178x447
6642	Customer	Joshua	Massey	kennethpena@example.net	)rH!greUl6	(580)389-1482
8158	PropertyManager	Justin	Cherry	meghananderson@example.com	_7G*tWMn^s	801.691.1610x96827
2247	Customer	Janice	Swanson	yvaughn@example.com	^*4^6VacZj	382-230-8718x01982
441	Customer	Jason	Gardner	martinezchristopher@example.com	+6LOiG7j$!	001-989-476-9375x3086
8910	Customer	Debra	Buchanan	chapmantyler@example.net	Vs(5*Pyg$I	+1-342-570-2273x45687
6362	Customer	Sylvia	Lloyd	strongdavid@example.org	&tXCax5S8w	(694)564-4711x70268
3204	Customer	Joel	Williams	morrismatthew@example.org	1JSOfKNd$1	001-897-700-1620x684
7828	Customer	Mackenzie	Andrews	wmendez@example.com	@j5Wwsyqyt	603.459.6967x671
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
-- Name: customer fk_customer_property; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk_customer_property FOREIGN KEY (propertyid) REFERENCES public.property(propertyid) ON DELETE SET NULL;


--
-- Name: maintenancerequest fk_maintenancerequest_property; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.maintenancerequest
    ADD CONSTRAINT fk_maintenancerequest_property FOREIGN KEY (propertyid) REFERENCES public.property(propertyid) ON DELETE CASCADE;


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

