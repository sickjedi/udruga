package org.tvornica_znanosti.udruga.web.rest;

import org.tvornica_znanosti.udruga.UdrugaApp;

import org.tvornica_znanosti.udruga.domain.Member;
import org.tvornica_znanosti.udruga.repository.MemberRepository;
import org.tvornica_znanosti.udruga.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.tvornica_znanosti.udruga.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.tvornica_znanosti.udruga.domain.enumeration.ReasonInactive;
/**
 * Test class for the MemberResource REST controller.
 *
 * @see MemberResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UdrugaApp.class)
public class MemberResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME = "BBBBBBBBBB";

    private static final String DEFAULT_OIB = "AAAAAAAAAA";
    private static final String UPDATED_OIB = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATEOFBIRTH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATEOFBIRTH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CELLPHONE = "AAAAAAAAAA";
    private static final String UPDATED_CELLPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATECREATED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATECREATED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_MEMBERFROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MEMBERFROM = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final LocalDate DEFAULT_DATEINACTIVE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEINACTIVE = LocalDate.now(ZoneId.systemDefault());

    private static final ReasonInactive DEFAULT_REASONINACTIVE = ReasonInactive.REQUEST;
    private static final ReasonInactive UPDATED_REASONINACTIVE = ReasonInactive.INACTIVE;

    private static final LocalDate DEFAULT_LASTUPDATED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LASTUPDATED = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMemberMockMvc;

    private Member member;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MemberResource memberResource = new MemberResource(memberRepository);
        this.restMemberMockMvc = MockMvcBuilders.standaloneSetup(memberResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Member createEntity(EntityManager em) {
        Member member = new Member()
            .name(DEFAULT_NAME)
            .surname(DEFAULT_SURNAME)
            .oib(DEFAULT_OIB)
            .dateofbirth(DEFAULT_DATEOFBIRTH)
            .address(DEFAULT_ADDRESS)
            .cellphone(DEFAULT_CELLPHONE)
            .email(DEFAULT_EMAIL)
            .datecreated(DEFAULT_DATECREATED)
            .memberfrom(DEFAULT_MEMBERFROM)
            .active(DEFAULT_ACTIVE)
            .dateinactive(DEFAULT_DATEINACTIVE)
            .reasoninactive(DEFAULT_REASONINACTIVE)
            .lastupdated(DEFAULT_LASTUPDATED);
        return member;
    }

    @Before
    public void initTest() {
        member = createEntity(em);
    }

    @Test
    @Transactional
    public void createMember() throws Exception {
        int databaseSizeBeforeCreate = memberRepository.findAll().size();

        // Create the Member
        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isCreated());

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeCreate + 1);
        Member testMember = memberList.get(memberList.size() - 1);
        assertThat(testMember.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMember.getSurname()).isEqualTo(DEFAULT_SURNAME);
        assertThat(testMember.getOib()).isEqualTo(DEFAULT_OIB);
        assertThat(testMember.getDateofbirth()).isEqualTo(DEFAULT_DATEOFBIRTH);
        assertThat(testMember.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testMember.getCellphone()).isEqualTo(DEFAULT_CELLPHONE);
        assertThat(testMember.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMember.getDatecreated()).isEqualTo(DEFAULT_DATECREATED);
        assertThat(testMember.getMemberfrom()).isEqualTo(DEFAULT_MEMBERFROM);
        assertThat(testMember.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testMember.getDateinactive()).isEqualTo(DEFAULT_DATEINACTIVE);
        assertThat(testMember.getReasoninactive()).isEqualTo(DEFAULT_REASONINACTIVE);
        assertThat(testMember.getLastupdated()).isEqualTo(DEFAULT_LASTUPDATED);
    }

    @Test
    @Transactional
    public void createMemberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = memberRepository.findAll().size();

        // Create the Member with an existing ID
        member.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().size();
        // set the field null
        member.setName(null);

        // Create the Member, which fails.

        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSurnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().size();
        // set the field null
        member.setSurname(null);

        // Create the Member, which fails.

        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOibIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().size();
        // set the field null
        member.setOib(null);

        // Create the Member, which fails.

        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateofbirthIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().size();
        // set the field null
        member.setDateofbirth(null);

        // Create the Member, which fails.

        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().size();
        // set the field null
        member.setAddress(null);

        // Create the Member, which fails.

        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCellphoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().size();
        // set the field null
        member.setCellphone(null);

        // Create the Member, which fails.

        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().size();
        // set the field null
        member.setEmail(null);

        // Create the Member, which fails.

        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDatecreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().size();
        // set the field null
        member.setDatecreated(null);

        // Create the Member, which fails.

        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberfromIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().size();
        // set the field null
        member.setMemberfrom(null);

        // Create the Member, which fails.

        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().size();
        // set the field null
        member.setActive(null);

        // Create the Member, which fails.

        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastupdatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRepository.findAll().size();
        // set the field null
        member.setLastupdated(null);

        // Create the Member, which fails.

        restMemberMockMvc.perform(post("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isBadRequest());

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMembers() throws Exception {
        // Initialize the database
        memberRepository.saveAndFlush(member);

        // Get all the memberList
        restMemberMockMvc.perform(get("/api/members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(member.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME.toString())))
            .andExpect(jsonPath("$.[*].oib").value(hasItem(DEFAULT_OIB.toString())))
            .andExpect(jsonPath("$.[*].dateofbirth").value(hasItem(DEFAULT_DATEOFBIRTH.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].cellphone").value(hasItem(DEFAULT_CELLPHONE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].datecreated").value(hasItem(DEFAULT_DATECREATED.toString())))
            .andExpect(jsonPath("$.[*].memberfrom").value(hasItem(DEFAULT_MEMBERFROM.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].dateinactive").value(hasItem(DEFAULT_DATEINACTIVE.toString())))
            .andExpect(jsonPath("$.[*].reasoninactive").value(hasItem(DEFAULT_REASONINACTIVE.toString())))
            .andExpect(jsonPath("$.[*].lastupdated").value(hasItem(DEFAULT_LASTUPDATED.toString())));
    }

    @Test
    @Transactional
    public void getMember() throws Exception {
        // Initialize the database
        memberRepository.saveAndFlush(member);

        // Get the member
        restMemberMockMvc.perform(get("/api/members/{id}", member.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(member.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME.toString()))
            .andExpect(jsonPath("$.oib").value(DEFAULT_OIB.toString()))
            .andExpect(jsonPath("$.dateofbirth").value(DEFAULT_DATEOFBIRTH.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.cellphone").value(DEFAULT_CELLPHONE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.datecreated").value(DEFAULT_DATECREATED.toString()))
            .andExpect(jsonPath("$.memberfrom").value(DEFAULT_MEMBERFROM.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.dateinactive").value(DEFAULT_DATEINACTIVE.toString()))
            .andExpect(jsonPath("$.reasoninactive").value(DEFAULT_REASONINACTIVE.toString()))
            .andExpect(jsonPath("$.lastupdated").value(DEFAULT_LASTUPDATED.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMember() throws Exception {
        // Get the member
        restMemberMockMvc.perform(get("/api/members/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMember() throws Exception {
        // Initialize the database
        memberRepository.saveAndFlush(member);
        int databaseSizeBeforeUpdate = memberRepository.findAll().size();

        // Update the member
        Member updatedMember = memberRepository.findOne(member.getId());
        // Disconnect from session so that the updates on updatedMember are not directly saved in db
        em.detach(updatedMember);
        updatedMember
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .oib(UPDATED_OIB)
            .dateofbirth(UPDATED_DATEOFBIRTH)
            .address(UPDATED_ADDRESS)
            .cellphone(UPDATED_CELLPHONE)
            .email(UPDATED_EMAIL)
            .datecreated(UPDATED_DATECREATED)
            .memberfrom(UPDATED_MEMBERFROM)
            .active(UPDATED_ACTIVE)
            .dateinactive(UPDATED_DATEINACTIVE)
            .reasoninactive(UPDATED_REASONINACTIVE)
            .lastupdated(UPDATED_LASTUPDATED);

        restMemberMockMvc.perform(put("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMember)))
            .andExpect(status().isOk());

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeUpdate);
        Member testMember = memberList.get(memberList.size() - 1);
        assertThat(testMember.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMember.getSurname()).isEqualTo(UPDATED_SURNAME);
        assertThat(testMember.getOib()).isEqualTo(UPDATED_OIB);
        assertThat(testMember.getDateofbirth()).isEqualTo(UPDATED_DATEOFBIRTH);
        assertThat(testMember.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testMember.getCellphone()).isEqualTo(UPDATED_CELLPHONE);
        assertThat(testMember.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMember.getDatecreated()).isEqualTo(UPDATED_DATECREATED);
        assertThat(testMember.getMemberfrom()).isEqualTo(UPDATED_MEMBERFROM);
        assertThat(testMember.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testMember.getDateinactive()).isEqualTo(UPDATED_DATEINACTIVE);
        assertThat(testMember.getReasoninactive()).isEqualTo(UPDATED_REASONINACTIVE);
        assertThat(testMember.getLastupdated()).isEqualTo(UPDATED_LASTUPDATED);
    }

    @Test
    @Transactional
    public void updateNonExistingMember() throws Exception {
        int databaseSizeBeforeUpdate = memberRepository.findAll().size();

        // Create the Member

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMemberMockMvc.perform(put("/api/members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(member)))
            .andExpect(status().isCreated());

        // Validate the Member in the database
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMember() throws Exception {
        // Initialize the database
        memberRepository.saveAndFlush(member);
        int databaseSizeBeforeDelete = memberRepository.findAll().size();

        // Get the member
        restMemberMockMvc.perform(delete("/api/members/{id}", member.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Member.class);
        Member member1 = new Member();
        member1.setId(1L);
        Member member2 = new Member();
        member2.setId(member1.getId());
        assertThat(member1).isEqualTo(member2);
        member2.setId(2L);
        assertThat(member1).isNotEqualTo(member2);
        member1.setId(null);
        assertThat(member1).isNotEqualTo(member2);
    }
}
