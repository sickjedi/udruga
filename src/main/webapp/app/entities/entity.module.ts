import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { UdrugaMemberModule } from './member/member.module';
import { UdrugaCustomerModule } from './customer/customer.module';
import { UdrugaInvoiceModule } from './invoice/invoice.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        UdrugaMemberModule,
        UdrugaCustomerModule,
        UdrugaInvoiceModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UdrugaEntityModule {}
