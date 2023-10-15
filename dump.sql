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
4931	Credit	\N	\N	f	\N
379	Credit	\N	\N	f	\N
6803	Credit	\N	\N	f	\N
5922	Cash	\N	\N	f	\N
5791	Cash	\N	\N	f	\N
780	Debit	\N	\N	f	\N
1400	Credit	\N	\N	f	\N
4372	Cash	\N	\N	f	\N
9359	Debit	\N	\N	f	\N
621	Cash	\N	\N	f	\N
3343	Credit	\N	\N	f	\N
3269	Cash	\N	\N	f	\N
9998	Debit	\N	\N	f	\N
4499	Credit	\N	\N	f	\N
6662	Credit	\N	\N	f	\N
9397	Credit	\N	\N	f	\N
2056	Cash	\N	\N	f	\N
5167	Credit	\N	\N	f	\N
5835	Cash	\N	\N	f	\N
694	Debit	\N	\N	f	\N
1385	Debit	\N	\N	f	\N
8576	Cash	\N	\N	f	\N
9684	Debit	\N	\N	f	\N
4533	Credit	\N	\N	f	\N
617	Cash	\N	\N	f	\N
5207	Cash	\N	\N	f	\N
8843	Cash	\N	\N	f	\N
8953	Credit	\N	\N	f	\N
3405	Cash	\N	\N	f	\N
3259	Debit	\N	\N	f	\N
284	Credit	\N	\N	f	\N
7441	Debit	\N	\N	f	\N
4641	Debit	\N	\N	f	\N
8726	Cash	\N	\N	f	\N
7257	Credit	\N	\N	f	\N
1974	Credit	\N	\N	f	\N
8750	Credit	\N	\N	f	\N
1881	Credit	\N	\N	f	\N
5519	Debit	2024-04-15	2025-02-23	t	990
3199	Credit	2023-06-02	2023-11-30	t	2064
2449	Debit	2023-05-08	2024-02-19	t	645
7646	Credit	2023-01-29	2023-08-13	t	454
5366	Cash	2024-08-23	2025-04-21	t	3205
8574	Cash	2023-10-04	2024-09-06	t	7395
8908	Credit	2023-06-22	2024-04-03	t	4439
5870	Debit	2023-06-02	2024-04-28	t	297
5852	Debit	2023-12-23	2024-09-29	t	2066
7992	Debit	2024-04-20	2025-03-12	t	968
26	Cash	2023-11-06	2024-06-23	t	1125
3286	Cash	2024-06-04	2025-01-10	t	6657
8738	Cash	2023-06-07	2023-12-21	t	5171
9255	Credit	2023-10-22	2024-08-01	t	8220
6779	Cash	2024-03-28	2024-10-23	t	5017
3590	Credit	2023-09-23	2024-08-05	t	934
5831	Credit	2023-03-07	2024-03-02	t	4637
3918	Credit	2024-07-26	2025-05-08	t	5801
8407	Debit	2024-01-01	2024-10-24	t	1995
42	Credit	2023-02-26	2023-10-20	t	9913
6688	Debit	2023-03-25	2024-01-06	t	417
4338	Debit	2022-11-04	2023-09-27	t	4762
1430	Credit	2023-08-31	2024-05-01	t	1049
957	Debit	2023-09-27	2024-06-02	t	615
4754	Debit	2024-05-06	2025-04-01	t	6876
1577	Credit	2024-03-21	2024-11-06	t	9836
2243	Cash	2024-02-10	2024-12-19	t	9752
3996	Cash	2024-09-03	2025-08-02	t	6983
6573	Debit	2024-04-13	2025-02-25	t	4211
613	Cash	2024-06-29	2025-06-27	t	8557
5619	Credit	2023-03-11	2023-10-14	t	5121
2256	Credit	2024-03-23	2024-11-14	t	1936
1581	Cash	2024-06-09	2025-05-16	t	6283
5008	Debit	2024-07-09	2025-03-01	t	3313
2524	Debit	2023-11-07	2024-06-26	t	7489
6430	Credit	2024-06-12	2025-03-23	t	294
4446	Cash	2024-08-21	2025-07-19	t	4855
5896	Credit	2024-07-16	2025-02-26	t	8404
3135	Cash	2022-11-08	2023-10-28	t	5156
3402	Credit	2023-10-09	2024-10-05	t	7188
2521	Cash	2023-06-27	2024-02-29	t	1494
208	Cash	2022-11-24	2023-08-05	t	6944
\.


--
-- Data for Name: maintenancerequest; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.maintenancerequest (isdealtwith, requestid, "timestamp", unitid, userid) FROM stdin;
f	3149	2021-05-24	7489	2524
t	4924	2020-09-03	4211	6573
f	8679	2020-11-19	9836	1577
t	4986	2021-01-01	6870	\N
f	8203	2023-08-02	1125	26
f	4875	2021-01-23	9666	\N
f	8782	2023-07-12	9913	42
t	3279	2023-03-28	645	2449
t	3416	2021-05-10	2066	5852
f	3281	2020-04-18	615	957
t	2951	2020-02-24	6944	208
t	6617	2021-01-12	1049	1430
f	295	2021-01-09	990	5519
t	5549	2021-11-07	4439	8908
f	4133	2023-07-25	2066	5852
t	3461	2020-06-25	6283	1581
t	6946	2021-07-23	294	6430
f	62	2022-03-03	294	6430
f	9880	2022-01-26	6283	1581
t	7196	2021-06-15	6870	\N
f	8595	2022-08-01	294	6430
t	8290	2020-02-19	5017	6779
f	2322	2022-03-24	7208	\N
f	8223	2021-04-22	3313	5008
t	9470	2021-01-22	2064	3199
t	5200	2022-12-13	1936	2256
t	5973	2022-07-01	7489	2524
f	2959	2021-08-29	5121	5619
t	5299	2020-12-15	5121	5619
f	9037	2020-06-08	6870	\N
\.


--
-- Data for Name: property; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.property (amenities, propertyid, address, rentduedate, name, communityannouncements) FROM stdin;
{pool,"dog park",park,playground,spa,gym,"BBQ area","secured parking"}	5166	9671 Cisneros Brooks Suite 976, Johnchester, AR 00719	2024-02-19	Mack, Lopez and Castro	{"Eat network manage that movie value."}
{pool,park,"dog park",playground,"secured parking",spa,"tennis courts"}	2395	55646 Catherine Grove, Longport, LA 32818	2023-09-11	King, Davis and Parker	{"Hand inside friend mention treat gun house."}
{"dog park",pool,park,"BBQ area",spa,"tennis courts",playground}	9519	1374 Harvey Flats, West Audreyland, AK 05020	2022-12-19	Thomas and Sons	{"Way while radio program approach.","Spring think leg image TV four hard.","Movement certain wall I production."}
{"BBQ area",playground}	950	694 Harris Falls Suite 494, North Aimeechester, VT 09275	2023-11-25	Mata-Harris	{"Edge this serious bed.","Note capital person direction music white."}
{"BBQ area",park,gym,"dog park",pool,playground,"tennis courts"}	9134	7220 Michele Villages Suite 926, New Michael, MD 86896	2024-02-19	Sullivan-Martinez	{"Military arrive together join subject order."}
{"tennis courts",pool,clubhouse,"BBQ area",playground}	6002	64774 Jennifer Plains, Dennisberg, IL 41343	2022-12-04	Coleman, Evans and Shields	{"Someone class every off of film.","Respond note daughter water yes space.","Way those its door."}
{"secured parking","tennis courts","dog park",pool}	4387	761 Melissa Terrace, Singhland, VA 33401	2023-03-29	Mcdowell-Fischer	{"Speak rise interview pay.","Government action until model."}
{"BBQ area",playground}	8437	25597 Tina Gardens Suite 408, Karenfort, KS 89040	2024-04-01	Brandt-Watson	{"Challenge statement group west receive seem wrong."}
{"secured parking",gym,"dog park",park,playground,"tennis courts","BBQ area",clubhouse,spa}	4300	721 Laura Ports, East Nicoleville, MT 54917	2024-09-09	Pace, Wolf and Ford	{"Scientist never nor student accept.","Site out stock network."}
{spa,"tennis courts","BBQ area",park,playground,"secured parking"}	9049	Unit 0540 Box 8011, DPO AP 00920	2024-08-14	Taylor-Lewis	{"Suffer concern box third like care."}
{"secured parking",clubhouse,"tennis courts",pool}	2701	63500 Eugene Tunnel, Mariafort, MP 41497	2024-06-05	Bailey LLC	{"Dog stand tend impact interest."}
{spa,clubhouse,playground,gym,"secured parking","BBQ area","tennis courts",pool}	3517	439 Jared Dam, Larsenberg, AL 19167	2023-03-28	Liu Inc	{"Her here station according actually son.","Who trade worry as house pass."}
{"BBQ area",park}	6169	211 Romero Crest Apt. 845, Joelhaven, NC 34783	2023-10-13	Wiley, Nash and Owens	{"Certainly do apply certain guess modern guess."}
{park,pool,playground,"secured parking"}	5359	59063 Mullen Passage, East Shelby, MH 47585	2023-03-24	Brown Inc	{"Over law skill bag some cause new.","Itself security top him arrive."}
{pool,park,"secured parking",playground,"tennis courts","dog park",spa,"BBQ area"}	7867	3724 Brian Fords, Dennisville, NM 63205	2022-11-29	Brandt, Klein and Hayes	{"Amount mind condition himself station.","Candidate possible true listen participant.","Appear girl commercial."}
{spa,clubhouse,gym,playground,pool,"secured parking","dog park","tennis courts"}	1725	994 Carpenter Greens, Hectormouth, FM 99173	2023-03-31	Wright, Watson and Avery	{"Practice sit any then.","Might everybody foreign."}
{"BBQ area",clubhouse,park,playground,gym,"tennis courts",pool,"dog park"}	9021	5069 Gaines Crossing Apt. 848, North Kimberly, WV 48877	2024-03-18	Copeland-Hall	{"Study before with experience."}
{gym,pool,park,"secured parking",spa,"dog park","tennis courts",playground,clubhouse,"BBQ area"}	5778	7886 Martin Harbors Suite 619, Ericstad, PR 56143	2024-06-15	Smith-Wiley	{"President effect avoid assume election.","Cause mouth how enter agency example hit."}
{spa,park,"BBQ area",clubhouse,playground,"tennis courts","dog park",pool,"secured parking",gym}	5994	4768 Nicole Circles, Adamsfort, GU 79455	2024-04-18	Johnson-Miles	{"Another whether dark special.","Science simple business pass should decade."}
{spa,gym,clubhouse}	8122	31090 Rodriguez Rapids, North Chloe, UT 22375	2024-07-20	Brock-Griffith	{"Program face here low upon only none discuss."}
\.


--
-- Data for Name: propertymanager; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.propertymanager (userid, isowner, propertyid) FROM stdin;
2084	f	1725
9874	t	8122
8034	t	6002
5001	f	5778
2469	f	9134
6456	t	2701
6068	t	9049
6475	f	2395
3928	t	6002
2272	f	6002
5980	f	9519
3502	f	6002
8118	t	2701
9918	t	9049
5482	f	4300
4459	f	7867
1617	f	8122
9792	f	4300
4201	t	9049
1557	f	7867
\.


--
-- Data for Name: unit; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.unit (unitid, isfurnished, rentamount, floorplan, condition, isrented, appliances, propertyid, rentpaid, rentdue, userid) FROM stdin;
990	f	1208	3 Bedroom	Used	t	{Dryer,"Coffee Maker",Refrigerator}	8437	f	2023-10-13	5519
2064	f	1883	3 Bedroom	New	t	{"Coffee Maker"}	7867	f	2023-10-13	3199
645	t	2968	Studio	New	t	{Oven}	9049	t	2023-10-13	2449
454	f	894	Studio	New	t	{Dryer,Oven}	4300	f	2023-10-13	7646
3205	f	828	1 Bedroom	New	t	{Microwave,"Coffee Maker","Washing Machine"}	9519	f	2023-10-13	5366
7395	t	1168	Loft	Used	t	{Microwave,Refrigerator,Blender,Dryer}	5778	f	2023-10-13	8574
4439	t	1654	2 Bedroom	New	t	{Blender}	950	t	2023-10-13	8908
297	f	1804	2 Bedroom	Old	t	{Blender,Toaster,"Washing Machine",Oven}	9049	f	2023-10-13	5870
2066	f	1634	2 Bedroom	Old	t	{Oven}	2701	t	2023-10-13	5852
968	t	642	Loft	Old	t	{Dryer,Toaster,Oven}	9519	f	2023-10-13	7992
1125	f	2859	Loft	New	t	{Dishwasher,Microwave,"Coffee Maker"}	950	f	2023-10-13	26
6657	t	2071	Studio	New	t	{Blender,"Coffee Maker",Toaster,Refrigerator}	6169	f	2023-10-13	3286
5171	t	1929	Penthouse	Old	t	{Toaster}	950	f	2023-10-13	8738
8220	t	2648	3 Bedroom	Old	t	{Refrigerator,"Coffee Maker",Dryer}	5166	t	2023-10-13	9255
5017	f	2782	Penthouse	New	t	{Dishwasher,"Coffee Maker"}	3517	t	2023-10-13	6779
934	t	2736	3 Bedroom	Good	t	{Microwave,Dryer,"Coffee Maker",Dishwasher}	1725	f	2023-10-13	3590
4637	f	721	2 Bedroom	Good	t	{Microwave,"Washing Machine",Blender}	6169	t	2023-10-13	5831
5801	f	1728	Studio	New	t	{"Washing Machine",Blender,"Coffee Maker",Microwave}	2701	t	2023-10-13	3918
1995	t	1177	2 Bedroom	Old	t	{Toaster,Blender}	5166	f	2023-10-13	8407
9913	t	2031	1 Bedroom	Good	t	{Dryer,"Washing Machine",Dishwasher,Blender}	4300	f	2023-10-13	42
417	t	1782	Penthouse	Good	t	{Oven,Dishwasher}	5166	f	2023-10-13	6688
4762	t	2815	1 Bedroom	Old	t	{"Washing Machine",Dishwasher}	8437	t	2023-10-13	4338
1049	t	2686	Penthouse	Good	t	{Oven,Toaster}	5166	f	2023-10-13	1430
615	t	2221	3 Bedroom	New	t	{Oven,Toaster,Dishwasher,Blender}	950	t	2023-10-13	957
6876	t	2976	1 Bedroom	Old	t	{Blender,Microwave}	9049	f	2023-10-13	4754
9836	t	585	Studio	Old	t	{Blender,Toaster}	5359	f	2023-10-13	1577
9752	f	2476	3 Bedroom	Old	t	{"Washing Machine",Microwave,Toaster}	7867	t	2023-10-13	2243
6983	f	886	1 Bedroom	New	t	{Oven,Dryer}	7867	f	2023-10-13	3996
4211	t	2125	Loft	Old	t	{"Coffee Maker",Oven,Toaster,Microwave}	5166	t	2023-10-13	6573
8557	t	2868	3 Bedroom	Used	t	{Dryer}	8437	t	2023-10-13	613
5121	t	2639	Loft	Good	t	{Dishwasher}	2701	t	2023-10-13	5619
1936	f	2591	Loft	Used	t	{Oven}	5994	t	2023-10-13	2256
6283	t	1210	3 Bedroom	Good	t	{Oven,Dryer,Dishwasher}	7867	f	2023-10-13	1581
3313	t	2569	Studio	Old	t	{Microwave,Toaster}	5994	f	2023-10-13	5008
7489	f	1530	1 Bedroom	Used	t	{Refrigerator}	7867	t	2023-10-13	2524
294	t	2837	2 Bedroom	Good	t	{Dryer}	9134	t	2023-10-13	6430
4855	t	2636	2 Bedroom	Used	t	{Oven,Toaster,Dishwasher,Refrigerator}	6169	f	2023-10-13	4446
8404	f	1248	2 Bedroom	Old	t	{Microwave,"Washing Machine","Coffee Maker",Refrigerator}	3517	t	2023-10-13	5896
5156	f	2225	Loft	Old	t	{Dryer,Refrigerator,"Washing Machine"}	2395	t	2023-10-13	3135
7188	f	1849	3 Bedroom	Old	t	{"Washing Machine",Dishwasher,Microwave}	8437	f	2023-10-13	3402
1494	t	838	Penthouse	New	t	{Dryer,Oven}	7867	f	2023-10-13	2521
6944	t	948	3 Bedroom	New	t	{Toaster,Dishwasher,Refrigerator}	9021	f	2023-10-13	208
6870	f	1043	1 Bedroom	Old	f	{"Coffee Maker"}	5778	f	2023-10-13	\N
398	t	1541	Penthouse	Used	f	{Refrigerator,"Coffee Maker",Dryer}	5359	f	2023-10-13	\N
4645	f	2336	Penthouse	Old	f	{Oven}	1725	f	2023-10-13	\N
9666	f	1934	Penthouse	Old	f	{"Washing Machine",Dryer}	2395	t	2023-10-13	\N
7208	f	2830	Studio	Good	f	{Microwave,Blender}	8122	f	2023-10-13	\N
9059	f	1583	Penthouse	Used	f	{Oven,Refrigerator}	5166	f	2023-10-13	\N
4947	f	937	3 Bedroom	Used	f	{Dryer,Toaster,Oven}	9049	t	2023-10-13	\N
6532	t	2134	Studio	Good	f	{Microwave,"Washing Machine",Refrigerator}	5994	f	2023-10-13	\N
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.users (userid, role, firstname, lastname, email, password, phonenumber) FROM stdin;
4931	Customer	Patrick	Martinez	reginapeters@example.com	7#uj4#xztM	(809)349-0060x6811
379	Customer	Benjamin	Sullivan	lsmith@example.org	Oi2NYdl)#T	784-785-5085x378
6803	Customer	Kevin	Wolfe	alexaadams@example.com	@Cd34Sf*FG	(514)623-2953x1647
5922	Customer	Alexander	Nelson	robertlin@example.org	#S#0+Abude	7488776403
4338	Customer	Kenneth	Baird	jamie45@example.org	&8LZyxIW*b	+1-370-621-8262x238
2084	PropertyManager	Megan	Martin	kramernathaniel@example.com	9XznJqNv(#	671.561.4850x93873
613	Customer	Craig	Reyes	sandralee@example.net	^%g+q0Tbia	(945)506-1186x570
2256	Customer	Vicki	Phillips	hudsonautumn@example.net	(sZISL4$&6	(988)516-2237
4754	Customer	Elizabeth	Freeman	kenneth75@example.org	z)xA4vYdj5	+1-680-391-9292
5791	Customer	Travis	Parker	karen36@example.net	%F9%ER@mw4	(627)290-6351
780	Customer	Theresa	Nichols	christine06@example.net	w!I7Yfns^0	001-445-645-9464x549
1400	Customer	Brent	Hebert	john14@example.com	%m@dW1IgC7	(701)529-2132x8525
9874	PropertyManager	Samantha	Thomas	ufranklin@example.net	r+8JtNxJ1y	616.540.4221
4372	Customer	Phillip	Christensen	nbennett@example.com	NJ%5WzshYz	(369)885-0809
5852	Customer	David	Mendoza	copelandchristina@example.com	l&6yFD5qb5	995-855-0231x97269
3590	Customer	Michelle	Smith	hoffmanrobert@example.net	^5kVIxEcZA	788-860-6171
208	Customer	Jeffrey	Gilbert	brandonhernandez@example.org	#57xDmYk)6	(952)289-6899
9359	Customer	Linda	Anderson	lrobinson@example.net	TN7YM)Wj@s	+1-311-428-7449x00061
8034	PropertyManager	Kimberly	Morris	ernesthunter@example.org	9+!+0U4s!2	+1-549-801-9262x52001
621	Customer	Charlene	Lowe	malonestephen@example.com	8M**K8$k_W	001-621-488-1449x35018
7992	Customer	Lisa	Garza	sturner@example.org	(1oWS_7vpS	725-898-6212x331
3343	Customer	Patrick	Harris	tony65@example.org	2+w42TdddY	3726713097
4446	Customer	Anna	Long	vanessa72@example.net	*7zyUkgJ_y	269.872.4357x27432
5001	PropertyManager	Maxwell	Riley	melaniereyes@example.com	74JnrfiN&s	611.280.3826
26	Customer	Susan	Campbell	heatherhowell@example.net	t#kW8SvyxC	(971)705-0460x61552
3269	Customer	Kenneth	Gonzalez	anthony84@example.net	8WLVT$v$@6	+1-971-831-2956x57229
9998	Customer	Kristen	Fisher	porterdavid@example.net	*Z#0V#bSEg	560-355-2811x677
4499	Customer	Adam	Walker	taylorangela@example.net	8T#55TlQF(	312-328-5254
8738	Customer	Kari	Lewis	burnscarl@example.net	a&0VdHXjw)	484-570-3673x95602
6662	Customer	Christopher	Yang	jennifertran@example.net	_5niAhDagv	(899)893-2777x06765
9397	Customer	Victoria	Snyder	leslie52@example.net	%26M7&Xi)S	823.659.8971x10486
2469	PropertyManager	Jimmy	Leonard	claire38@example.com	5v6D0oNn(s	6787214061
2056	Customer	Earl	Marshall	craigochoa@example.org	Xe5LzKqa_f	711.642.8920x1432
6456	PropertyManager	Jeremy	Lewis	matthew50@example.org	ll(S3WYv$s	(819)531-9396
5519	Customer	Rebecca	Thompson	jeffreyjohnson@example.com	B4TUjaVd&j	(845)315-5806
6068	PropertyManager	Diana	Reynolds	danielleyoung@example.org	G3Cl7Tii_+	001-905-348-5441x67700
6475	PropertyManager	Brittany	Dodson	awinters@example.org	o@N11XnC@O	(589)202-8815x770
3928	PropertyManager	Stephanie	Massey	oscar82@example.org	s1bIqJ6!#n	+1-433-863-2247x1531
5870	Customer	Trevor	Salazar	thomaskatherine@example.com	N@x9SPs%Fg	773.304.3110x557
5831	Customer	Jason	Velazquez	reyesheather@example.net	wQf^7Zel_9	2949868550
3135	Customer	Jill	Evans	hmatthews@example.com	*nygG4sqR2	+1-396-903-2474x03528
5167	Customer	Sylvia	Williams	dawnbenson@example.org	E3$At7+t(_	501-434-2320
5835	Customer	Joseph	Owens	fross@example.com	sC_^2Rgtz+	623-979-3181x2591
2272	PropertyManager	Samantha	Ortega	barbaraarellano@example.net	GtV8WM#cs^	275.805.3832
694	Customer	Alicia	Austin	burkenathaniel@example.net	AJ7&sNJz!+	+1-691-939-8138x9166
1385	Customer	Sherry	Olsen	smithstephanie@example.com	8#eZ5)Z&fa	572.308.5243
5980	PropertyManager	Eric	Payne	shelbymiller@example.net	*PWnl6@W)6	(594)586-9873
8576	Customer	Amber	Brown	jbutler@example.net	%KVO5Idt27	500.480.2612
9684	Customer	Nathan	Moore	uwright@example.net	_Rr!7gOteI	001-475-724-3516x76719
4533	Customer	Alicia	Barry	riveraryan@example.net	6%P3GG&gC(	(832)785-1454x1168
3996	Customer	Kayla	Mckay	roger77@example.com	S^5&GsPgol	(350)884-3236x72030
5008	Customer	Charlotte	Hughes	dhogan@example.com	8m%#Yak1$(	341.285.2484x869
3502	PropertyManager	Kristen	Taylor	douglascrane@example.net	_u9vEgif34	9277867923
8574	Customer	Evan	Campos	williamsdana@example.net	nn0S4((H*_	606.379.9054
6573	Customer	Eric	Haney	jonathan73@example.com	0333eLHqc)	8944001463
617	Customer	Julie	Underwood	eharrison@example.com	&4Jt_ZP99s	895-273-0974x3502
8118	PropertyManager	Kevin	Duran	nicholas20@example.net	ZJ*F5OhH_Y	698.423.2163
2524	Customer	Glen	Morris	jessica49@example.net	8QDZGb#p%s	8806645403
1430	Customer	April	Barrett	yrollins@example.com	zlm7Px!1M!	269.548.7148
5207	Customer	Robert	Bean	peterstein@example.net	+Ik1xSIzk$	001-783-954-3246x2174
3402	Customer	Michael	Bass	richard32@example.org	5p70L8__R+	587-438-8524
9918	PropertyManager	Natasha	Shelton	williamsveronica@example.net	TgU8Iy_Jc_	679-917-3828x823
9255	Customer	Luis	Gaines	dsmith@example.org	$P63sFUfL8	510.385.6655
5482	PropertyManager	Maria	Nelson	elliottbrittany@example.org	zUn&7Fbc!1	766.831.3296
6430	Customer	Brittany	Morgan	rwest@example.com	_8IKR(yO+U	703.705.3681
2449	Customer	Sarah	Jones	lharris@example.org	)Ka%8Dux%U	255-948-1882
3286	Customer	Jacqueline	Cain	tgonzalez@example.org	Q1LkkCLd%U	(361)992-6724
1581	Customer	William	Ross	englishkevin@example.org	@81yIQCm&#	001-240-574-3462x8213
5896	Customer	Carol	Stevens	chadhahn@example.com	oWArQuzF_0	329-866-4446
2243	Customer	Anna	Thompson	daniel68@example.net	%0JOMPnitZ	001-770-872-0707
8843	Customer	Carol	Gonzalez	martinezjoan@example.net	L7yCko4U%6	564.587.7143x415
8953	Customer	Christopher	Walton	jeffersonnicole@example.net	KjY66aYD^$	929.702.5787
3199	Customer	Erin	Lopez	jessica99@example.net	m*0XT+xt8w	+1-822-286-0704x25784
957	Customer	Alexander	Jackson	vbrown@example.net	+4WGDrVe75	6659872144
3405	Customer	Amanda	Scott	johnsonjoseph@example.net	m4IFd6HG@U	4589458690
3259	Customer	Veronica	Taylor	amiller@example.com	%H2^7yPbQ%	(595)411-4542
284	Customer	Tyler	Jackson	josesmith@example.com	T^NSVrSk*1	+1-729-765-1760
7441	Customer	Kevin	Oneill	dallison@example.org	1BS4yFyo*M	763.484.3988x69919
7646	Customer	Matthew	Goodman	pereztonya@example.com	%3F03S$gQ^	(905)364-0292
5366	Customer	Jessica	Collins	umathis@example.net	+30X7m4rKv	+1-640-571-1023x1288
8407	Customer	Stacey	Stone	chungheather@example.com	G2$8LqQ7)(	+1-425-869-8121
6779	Customer	Holly	Elliott	cody29@example.org	y^7Jj4taN&	(357)632-6951x130
5619	Customer	Stacy	Black	moodymelanie@example.com	3YTggp^v&7	5737550920
4459	PropertyManager	Timothy	Ross	dawn48@example.org	&2s%PdD_%%	(397)533-8430
42	Customer	Hannah	Bennett	kbarnes@example.org	M7Exo$Q+_q	(817)548-7254x6801
4641	Customer	Barbara	Ramirez	elliottdanny@example.org	Qq1RRl(yf^	+1-450-357-2622x484
8726	Customer	Karen	Carlson	alan32@example.org	tmI3D(zAl!	340.484.6548
1617	PropertyManager	Matthew	Davis	robertstuart@example.net	ekBZ1^GA@8	978-378-7137
7257	Customer	Kelly	Duncan	bakeralicia@example.org	58WO4x(D^F	248-582-4486
2521	Customer	Jason	Woods	gomezcaleb@example.org	#p^KdX(db5	001-960-356-6580x4470
6688	Customer	Heidi	Williams	edgar92@example.net	C18)7wNy%I	406.843.0754
1577	Customer	Jacqueline	Mccarthy	tyler19@example.net	5*5GVOx73%	533-486-3124x57275
9792	PropertyManager	Kenneth	Anderson	ocollins@example.net	1fCl!wDY(3	+1-659-299-9913x855
1974	Customer	Kayla	Phillips	dariusprice@example.net	)nIc#V+o%6	+1-536-744-2123x597
3918	Customer	Lauren	Stafford	flemingtyler@example.org	2sw@3JMd*1	202.494.4266x519
8908	Customer	Diana	Miranda	dbean@example.net	0Qtnh0zF$Y	(580)538-8250x15357
8750	Customer	Matthew	Mathews	sandovaljohn@example.com	r4Ev)n5T#K	977.459.2455x6127
1881	Customer	Carrie	Nguyen	hdurham@example.net	&x@YsIuD46	(268)984-8710x2984
4201	PropertyManager	Robert	Andersen	pagekathleen@example.net	OXY7NHmIz*	+1-985-909-4642
1557	PropertyManager	Joshua	Rogers	munozshawn@example.org	@wk8kxAts7	5129905817
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

